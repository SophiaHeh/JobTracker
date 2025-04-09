package com.tnite.jobwinner.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tnite.jobwinner.bean.SophiaUser;
import com.tnite.jobwinner.mapper.SophiaUserMapper;
import com.tnite.jobwinner.service.SophiaUserService;
import org.springframework.stereotype.Service;

@Service
public class SophiaUserServiceImpl extends ServiceImpl<SophiaUserMapper, SophiaUser> implements
    SophiaUserService {
}