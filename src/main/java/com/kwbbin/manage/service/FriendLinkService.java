package com.kwbbin.manage.service;

import com.kwbbin.bean.FriendLink;

import java.util.List;

public interface FriendLinkService {
    List<FriendLink> getAllFriendLink();

    Integer deleteFriendLink(Integer id);

    Integer addFriendLink(FriendLink friendLink);

    Integer updateFriendLink(FriendLink friendLink);
}
