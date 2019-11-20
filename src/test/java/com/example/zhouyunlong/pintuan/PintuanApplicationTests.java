package com.example.zhouyunlong.pintuan;
import com.example.zhouyunlong.pintuan.dao.*;
import com.example.zhouyunlong.pintuan.entity.*;
import com.example.zhouyunlong.pintuan.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
class PintuanApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private GroupMemberMapper groupMemberMapper;
    @Autowired
    private JoinPintuanMapper joinPintuanMapper;
    @Autowired
    private GroupJoinMapper groupJoinMapper;
    @Autowired
    private MemberService memberService;

    @Test
    public void test() {
        List<MsmpUser> msmpUserList = userMapper.selectList(null);
        msmpUserList.forEach(System.out::println);
    }
    @Test
    public void save() {
        MsmpUser msmpUser = MsmpUser.builder().name("张国立1").build();

        int user = userMapper.insert(msmpUser);
    }

    @Test
    public void delete(){
        int user = userMapper.deleteById(0);
        System.out.println(user);
    }
//    @Test
//    public void updateUser(){
//        MsmpUser msmpUser = MsmpUser.builder().name("大龙123").build();
//        LambdaQueryWrapper<MsmpUser> lambdaQueryWrapper = new LambdaQueryWrapper();
//        lambdaQueryWrapper.eq(MsmpUser::getName, "大龙123");
//        MsmpUser newUser = userMapper.selectOne(lambdaQueryWrapper);
//
//
//        LambdaUpdateWrapper<MsmpUser> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
//        MsmpUser updateUser = MsmpUser.builder().name("网吧搞死" ).age(10).build();
//
//        lambdaUpdateWrapper.eq(MsmpUser::getId, newUser.getId());
//        int user = userMapper.update(updateUser, lambdaUpdateWrapper);
//        System.out.println(user);
//    }

    @Test
    public void createNewActivity() {
        Activity activity = new Activity();
        activity.setName("第一个拼团活动");
        activity.setContent("测试一下吧");

        int successNum = activityMapper.insert(activity);
    }

    @Test
    public void createNewMember() {
//        List<GroupMember> list = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            GroupMember groupMember = new GroupMember();
            groupMember.setName("MEMBER"+ i);
            int success = groupMemberMapper.insert(groupMember);
            System.out.println(success);
        }
    }

    @Test
    @Transactional
    public void joinPintuan() {

        GroupActivityRel rel = joinPintuanMapper.selectById("1193102750173974530");

    }
    @Test
    public void initiateGroup() {
        List<GroupActivityRel> users = joinPintuanMapper.selectByMemberIdForUpdate(1193102750173974530L);
        if(users.size() > 0){
            System.out.println("已经参加活动了");

        }else {
            GroupActivityRel groupActivityRel = new GroupActivityRel();
            groupActivityRel.setGroupMemberId(1193102750173974530L);
            groupActivityRel.setActivityId(1193099515480592386L);
            groupActivityRel.setAsKing(1);
            groupActivityRel.setJoinNum(1);
            //1 == 拼团中
            groupActivityRel.setStatus(1);
            groupActivityRel.setCreateTime(new Date());
            groupActivityRel.setUpdateTime(new Date());
            joinPintuanMapper.insert(groupActivityRel);
        }
    }


//    @Test
//    public void joinGroup() {
//        //通过即将参加活动的人的userId判断是否发起了拼团，如果发起了，直接返回；
////        List<GroupActivityRel> users = joinPintuanMapper.
////                selectByMemberIdAndGroupIdForUpdate(1193102750173974530L,1193153121911160833L);
//        Long memberId = 1193102750610182145L;
////        List<GroupActivityRel> users = joinPintuanMapper.selectByMemberIdForUpdate(memberId);
//        GroupActivityRel pintuanActivity = joinPintuanMapper.selectByGroupIdForUpdate(1193153121911160833L);
//        if(pintuanActivity != null && memberId.equals(pintuanActivity.getGroupMemberId()) ){
//            System.out.println("你已经发起了一个拼团，无法在参加拼团了");
//            return ;
//        }else {
//            // 如果不是发起人，1、要用活动id查询发起人的团是否存在
////            GroupActivityRel rel = joinPintuanMapper.selectByGroupIdForUpdate(1193153121911160833L);
//            if(null == pintuanActivity){
//                System.out.println("您参加的这个活动不存在");
//                return ;
//            }
//            else{
//                if(pintuanActivity.getJoinNum() >= 5){
//                    System.out.println("活动人数已经满了，无法加入");
//                    return ;
//                }
//                else{
//                    QueryWrapper<GroupJoin> query = new QueryWrapper<>();
//                    query.eq("member_id",memberId);
//                    GroupJoin user = groupJoinMapper.selectOne(query);
//                    if(null != user){
//                        System.out.println("你已经参加活动了，无法再次加入");
//                        return ;
//                    }
//                    System.out.println("加入活动并更新活动人数");
//                    int num = pintuanActivity.getJoinNum()+1;
//                    pintuanActivity.setJoinNum(num);
//
//                    try {
//                        int updateSuccess = joinPintuanMapper.updateById(pintuanActivity);
//
//                        System.out.println("updateSuccess" + updateSuccess);
//                        if(updateSuccess == 1){
//                            System.out.println("更新成功");
//                        }else{
//                            System.out.println("更新失败");
//                        }
//                    }catch (Exception e){
//
//                    }
//
//                    try {
//                        GroupJoin groupJoin = new GroupJoin();
//                        groupJoin.setCreateTime(new Date());
//                        groupJoin.setGroupId(pintuanActivity.getId());
//                        groupJoin.setActivityId(pintuanActivity.getActivityId());
//                        groupJoin.setMemberId(memberId);
//                        int insertSuccess = groupJoinMapper.insert(groupJoin);
//                        if(insertSuccess == 1){
//                            System.out.println("参团成功");
//                        }else{
//                            System.out.println("参团失败");
//                        }
//                    }catch (Exception e){
//
//                    }
//
//
//                }
//                return;
//            }
////            GroupActivityRel groupActivityRel = new GroupActivityRel();
////            groupActivityRel.setGroupMemberId(1193102750173974530L);
////            groupActivityRel.setActivityId(1193099515480592386L);
////            groupActivityRel.setAsKing(1);
////            groupActivityRel.setJoinNum(1);
////            //1 == 拼团中
////            groupActivityRel.setStatus(1);
////            groupActivityRel.setCreateTime(new Date());
////            groupActivityRel.setUpdateTime(new Date());
////            joinPintuanMapper.insert(groupActivityRel);
//        }
//    }

    @Test
    void contextLoads() {
    }

    @Test
    void testJetCache() {
        GroupMember groupMember = memberService.getGroupMemberById(1193102750576627713L);
        System.out.println(groupMember);
    }

}
