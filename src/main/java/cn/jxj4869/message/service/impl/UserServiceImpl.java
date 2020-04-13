package cn.jxj4869.message.service.impl;

import cn.jxj4869.message.entity.User;
import cn.jxj4869.message.mapper.UserMapper;
import cn.jxj4869.message.service.IUserService;
import cn.jxj4869.message.utils.MainUtil;
import cn.jxj4869.message.utils.Util;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jxj4869
 * @since 2020-04-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    MainUtil mainUtil;

    @Override
    public boolean findByEmail(String email) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        User user = userMapper.selectOne(wrapper);
        return user == null;
    }

    @Override
    public String generatorCheckCode(String email) {
        String code = String.valueOf(Util.getRandomNumber(1000, 9999));
        String content = "您的验证码为：" + code;
        System.out.println(content);
        mainUtil.sendCheckCodeEmail(email, content);
        return code;
    }

    @Transactional
    @Override
    public boolean register(User user) {
        String avatar = Util.getAvatar();
        user.setAvatar(avatar);
        int id = userMapper.insert(user);
        System.out.println(user);
        return true;
    }

    @Override
    public User login(String userName, String password) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userName", userName);
        User user = userMapper.selectOne(wrapper);
        if (password != null && user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public boolean findByUserName(String userName) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userName", userName);
        User user = userMapper.selectOne(wrapper);
        System.out.println(userName);
        System.out.println(user);
        return user == null;
    }


}
