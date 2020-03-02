package com.yqyjwx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqyjcommon.entity.Collection;
import com.yqyjcommon.entity.Scenic;
import com.yqyjcommon.entity.User;
import com.yqyjwx.dao.CollectionDao;
import com.yqyjwx.dao.ScenicDao;
import com.yqyjwx.dao.UserDao;
import com.yqyjwx.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScenicServiceImpl extends ServiceImpl<ScenicDao, Scenic> implements ScenicService {

    @Autowired
    private ScenicDao scenicDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CollectionDao collectionDao;

    @Override
    public IPage<Scenic> recommendByDianzan(int pageNum,int pageSize) {

        Page<Scenic> scenicPage = new Page<>(pageNum, pageSize);
        QueryWrapper<Scenic> query = Wrappers.<Scenic>query();
        query.orderByDesc("scenic_love");
        Page<Scenic> page = scenicDao.selectPage(scenicPage, query);
        return page;
    }

    /**
     * 个性化推荐，协同过滤算法
     * @return
     */
    @Override
    public IPage<Scenic> recommendByUserphone(String user_id, int pageNum, int pageSize) {

        QueryWrapper<User> queryAllUser = Wrappers.<User>query();
        queryAllUser.select("id,user_phone,user_interest").last("LIMIT 10000"); // 防止数据量过大，限制1万条
        List<User> users = userDao.selectList(queryAllUser);
        HashMap<String, Map<String, Float>> similary_map = new HashMap<>();// 记录相似度的
        for(int i=0; i< users.size(); i++){
            HashMap<String, Float> sim_item = new HashMap<>();
           for (int j=0; j< users.size(); j++) {
               List<String> compare = Arrays.asList(users.get(i).getUserInterest().split(","));
               List<String> compare_with = Arrays.asList(users.get(j).getUserInterest().split(","));
               float s = 0;
               for (String scenic_type : compare) {
                   if (compare_with.contains(scenic_type)) {
                       s = s + 1.0F;
                   }
               }
               // s是相似度衡量指标，对指标s进行优化
               int compare_size =  compare.size();
               int compare_with_size =  compare_with.size();
               if (compare_size <= compare_with_size) {
                   s = ((float) compare_size/(float) compare_with_size) * s;
               } else {
                   s = ((float) compare_with_size/(float) compare_size) * s;
               }
               sim_item.put(String.valueOf(users.get(j).getId()), s);
           }
           similary_map.put(String.valueOf(users.get(i).getId()), sim_item);
       }

        System.out.println("所有相似度的计算结果展示--->");
        similary_map.forEach((k,v) -> {
            System.out.print(k + "  ---  ");
            System.out.println(v);
        });

        // similary_map是所有的相似度，此时根据 phone 拿到某个人和其他人的相似度
        Map<String, Float> oneUserSim = similary_map.get(user_id);

        // 对拿到的某个人相对其他人的相似度做排序
        System.out.println("-------------相似度排序前：------------->>>");
        System.out.println(oneUserSim);

        LinkedHashMap<String, Float> oneUserSimOrdered = oneUserSim.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e2,
                        LinkedHashMap::new));
        oneUserSimOrdered.remove(user_id); // 移除和自身的比较
        System.out.println("-------------相似度排序后：------------->>>");
        oneUserSimOrdered.forEach((k,v) -> System.out.println(k + "   " + v));

        ArrayList<Collection> recommend_collection = new ArrayList<>();

        // 拿取相似度最高的某个人，依次根据这若干个人去获取他们的收藏列表
        oneUserSimOrdered.forEach((k, v) -> {
            QueryWrapper<Collection> query = Wrappers.<Collection>query();
            query.eq("user_id", k);
            List<Collection> collections = collectionDao.selectList(query);
            recommend_collection.addAll(collections);
        });

        // 排除掉我已经收藏的
        List<Collection> my_collection = collectionDao.selectList(Wrappers.<Collection>query().eq("user_id", user_id));
        for (Collection collection : recommend_collection) {
            for (Collection collection1 : my_collection) {
                if (collection.getId() == collection1.getId()){
                    recommend_collection.remove(collection);
                }
            }
        }

        // 此时拿到所有应该推荐的景点，将应该推荐的推荐返回
        ArrayList<Integer> recomment_scenic_id = new ArrayList<>();
        for (Collection collection : recommend_collection) {
            recomment_scenic_id.add(collection.getScenicId());
        }

        List<Scenic> recomment_scenics = scenicDao.selectBatchIds(recomment_scenic_id);
        System.out.println("给用户的推荐列表------------>>");
        recomment_scenics.forEach(i -> System.out.println(i.getScenicName()));
        System.out.println("------------>>");
        // 封装到page里面
        Page<Scenic> scenicPage = new Page<>();
        scenicPage.setRecords(recomment_scenics.subList((pageNum-1)*pageSize,
                ((pageNum-1)*pageSize + pageSize)<recomment_scenics.size()?((pageNum-1)*pageSize + pageSize + 1) : (recomment_scenics.size())));
        scenicPage.setTotal(recomment_scenics.size());
        scenicPage.setCurrent(pageNum);
        scenicPage.setSize(pageSize);

        return scenicPage;
    }
}
