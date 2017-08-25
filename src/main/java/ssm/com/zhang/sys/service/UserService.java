package ssm.com.zhang.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm.com.zhang.sys.dao.UserMapper;
import ssm.com.zhang.sys.domain.User;

import java.util.List;

/**
  * 用户管理
  *
  * @author brian.zhang
  * @date 8/23/2017 14:08
  */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserService {

    @Autowired
    UserMapper userMapper;

    /**
      * 获取所有User对象
      *
      * @author brian.zhang
      * @param
      * @return java.util.List<ssm.com.zhang.sys.domain.User>
      * @date 8/23/2017 14:08
      */
    @Transactional(readOnly = true)
    public List<User> listAllUsers() {
        return userMapper.listAllUsers();
    }

}
