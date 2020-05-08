package com.kwbbin.manage.service;

import com.kwbbin.bean.FriendLink;
import com.kwbbin.bean.FriendLinkExample;
import com.kwbbin.dao.FriendLinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendLinkServiceImpl implements FriendLinkService {

    @Autowired
    FriendLinkMapper friendLinkMapper;

    @Override
    public List<FriendLink> getAllFriendLink() {
        return friendLinkMapper.selectByExample(new FriendLinkExample());
    }

    @Override
    public Integer deleteFriendLink(Integer id) {
        try {
            friendLinkMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            return -1;
        }
        return 0;
    }

    @Override
    public Integer addFriendLink(FriendLink friendLink) {
        try {
            friendLinkMapper.insertSelective(friendLink);
        }catch (Exception e){
            return -1;
        }
        return 0;
    }

    @Override
    public Integer updateFriendLink(FriendLink friendLink) {
        try {
            friendLinkMapper.updateByPrimaryKey(friendLink);
        }catch (Exception e){
            return -1;
        }
        return 0;
    }
}
