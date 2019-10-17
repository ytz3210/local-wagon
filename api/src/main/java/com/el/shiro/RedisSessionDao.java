package com.el.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@SuppressWarnings({"rawtypes", "unchecked"})
public class RedisSessionDao extends AbstractSessionDAO {
    // Session超时时间，单位为毫秒
    private long expireTime = 1800000;//30分钟
    private static String prefix = "wagon_tracker_shiro_redis_session:";


    // Redis操作类
    @Autowired
    private RedisTemplate redisTemplate;

    public RedisSessionDao() {
        super();
    }

    public RedisSessionDao(long expireTime, RedisTemplate redisTemplate) {
        super();
        this.expireTime = expireTime;
        this.redisTemplate = redisTemplate;
    }

    @Override // 更新session
    public void update(Session session) throws UnknownSessionException {
        //System.out.println("更新session:"+session.getId().toString());
        if (session == null || session.getId() == null) {
            return;
        }
        session.setTimeout(expireTime);
        redisTemplate.opsForValue().set(prefix + session.getId(), session, expireTime, TimeUnit.MILLISECONDS);
    }

    @Override // 删除session
    public void delete(Session session) {
        //System.out.println("删除session:"+session.getId().toString());

        if (null == session) {
            return;
        }
        redisTemplate.opsForValue().getOperations().delete(prefix + session.getId());
    }

    @Override// 获取活跃的session
    public Collection<Session> getActiveSessions() {
        //System.out.println("获取活跃的session");
        Set<String> keys = redisTemplate.keys(prefix + "*");
        Collection<Session> list = new ArrayList<>();
        if (keys != null && keys.size() > 0) {
            for (String item : keys) {
                Session session = (Session) redisTemplate.opsForValue().get(item);
                list.add(session);
            }
        }
        return list;

    }


    @Override// 加入session
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        //System.out.println("创建session:"+sessionId);
        redisTemplate.opsForValue().set(prefix + session.getId(), session, expireTime, TimeUnit.MILLISECONDS);
        return sessionId;
    }

    @Override// 读取session
    protected Session doReadSession(Serializable sessionId) {
        //System.out.println("读取session:"+sessionId.toString());
        if (sessionId == null) {
            return null;
        }
        return (Session) redisTemplate.opsForValue().get(prefix + sessionId.toString());
    }


    public void forceLogout(String sessionId) {

        redisTemplate.opsForValue().getOperations().delete(prefix + sessionId);
    }


    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
