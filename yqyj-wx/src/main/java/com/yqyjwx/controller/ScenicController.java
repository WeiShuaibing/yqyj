package com.yqyjwx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yqyjcommon.entity.Collection;
import com.yqyjcommon.entity.Scenic;
import com.yqyjcommon.pojo.MyPage;
import com.yqyjcommon.pojo.R;
import com.yqyjwx.service.CollectionService;
import com.yqyjwx.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/scenic")
public class ScenicController {

        @Autowired
        private ScenicService scenicService;
        @Autowired
        private CollectionService collectionService;

        /**
         * 根据类型type搜索
         */
        @GetMapping("/searchByType")
        public R searchByType(String type){
            QueryWrapper<Scenic> query = Wrappers.<Scenic>query();
            query.like("scenic_type", type);
            List<Scenic> list = scenicService.list(query);
            MyPage<Scenic> myPage = new MyPage<>(list, scenicService.count(query));
            return new R(myPage);
        }

        @GetMapping("/search")
        public R search(String matchStr){
            QueryWrapper<Scenic> query = Wrappers.<Scenic>query();
            query
            .like("scenic_name",matchStr).or()
            .like("scenic_addr",matchStr);
            List<Scenic> list = scenicService.list(query);
            MyPage<Scenic> myPage = new MyPage<>(list, scenicService.count(query));
            return new R(myPage);
        }

        @GetMapping("/recommend")
        public R recommend(@RequestParam(required = false) String user_id,int pageNum,int pageSize){
            IPage<Scenic> scenicIPage = null;
            if (user_id == null || user_id.equals("")) {
                // 直接查询点赞量最高的
                scenicIPage = scenicService.recommendByDianzan(pageNum, pageSize);

            } else {
                // 需要根据用户 推荐出最符合的 (后期需要完善此算法)
                scenicIPage = scenicService.recommendByUserphone(user_id, pageNum, pageSize);
            }
            return new R(scenicIPage);
         }

         @GetMapping("/getById")
         public R getById(int id){
             Scenic scenic = scenicService.getById(id);
             return new R(scenic);
         }

    /**
     * 点赞
     * @return
     */
    @GetMapping("/dianzan")
    public R dianzan(int id){
        Scenic scenic = scenicService.getById(id);
        scenic.setScenicLove(scenic.getScenicLove() + 1);
        scenicService.updateById(scenic);
        return new R();
    }

    @GetMapping("/selectLunbotuScenic")
    public R selectLunbotuScenic(){
        QueryWrapper<Scenic> query = Wrappers.<Scenic>query();
        query.eq("scenic_home_show",1).orderByDesc("id");
        List<Scenic> list = scenicService.list(query);
        return new R(list);
    }

    /**
     * 获取所有我的收藏的景点
     * @param user_id
     * @return
     */
    @GetMapping("/getAllMyLovedScenic")
    public R getAllMyLovedScenic(int user_id){
        QueryWrapper<Collection> queryCollections = Wrappers.<Collection>query();
        queryCollections.eq("user_id", user_id);
        List<Collection> listCollections = collectionService.list(queryCollections);
        ArrayList<Integer> lovedScenicId = new ArrayList<>();
        listCollections.forEach(i -> {
            lovedScenicId.add(i.getScenicId());
        });
        List<Scenic> scenics = null;
        if (lovedScenicId.size() != 0){
            scenics = scenicService.listByIds(lovedScenicId);
            return new R(scenics);
        } else {
            return new R();
        }

    }

    /*
    * 获取不同类型的景点，按照点赞量排行，最多返回8个
    * */
    @GetMapping("/getDifferentTypeSceic")
    public R getDifferentTypeSceic(String type){
        QueryWrapper<Scenic> query = Wrappers.<Scenic>query();
        query.like("scenic_type", type).last("LIMIT 8");
        List<Scenic> list = scenicService.list(query);
        return new R(list);
    }


    /**
     * 查找攻略
     * @param pageNum
     * @param pageSize
     * @param matchStr  如果为空，则查询所有，否则查询匹配度的数据
     * @return
     */
    @GetMapping("/searchGonglue")
    public R searchGonglue(int pageNum, int pageSize, String matchStr) {
        QueryWrapper<Scenic> query = Wrappers.<Scenic>query();
        query.like("scenic_strategy", matchStr)
                .or().like("scenic_name", matchStr)
                .or().like("scenic_addr", matchStr).orderByDesc("id");
        Page<Scenic> page = new Page<>(pageNum, pageSize);
        Page<Scenic> page1 = scenicService.page(page, query);
        return new R(page1);
    }

    @GetMapping("/testRecommend")
    public R testRecommend(String user_id, int pageNum, int pageSize){
        IPage<Scenic> scenicIPage = scenicService.recommendByUserphone(user_id, pageNum, pageSize);
        return new R(scenicIPage);
    }


    /**
     * 景点攻略点赞
     */
    @GetMapping("/scenicStrategyLoved")
    public R scenicStrategyLoved(int scenic_id) {
        Scenic byId = scenicService.getById(scenic_id);
        byId.setScenicStrategyLove(byId.getScenicStrategyLove() + 1);
        boolean b = scenicService.saveOrUpdate(byId);
        if (b) return new R();
        else return new R(20001,"服务异常，更新失败！");
    }


    /**
     * 按照攻略的点赞量排行推荐
     */
        @GetMapping("/recommentStrategyByLove")
    public R recommentStrategyByLove(int pageSize) {
        QueryWrapper<Scenic> query = Wrappers.<Scenic>query();
        query.orderByDesc("scenic_strategy_love").last("LIMIT "+pageSize);
        List<Scenic> list = scenicService.list(query);
        return new R(list);
    }

}
