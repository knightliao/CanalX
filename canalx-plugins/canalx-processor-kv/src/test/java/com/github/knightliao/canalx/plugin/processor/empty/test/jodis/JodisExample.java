package com.github.knightliao.canalx.plugin.processor.empty.test.jodis;

import io.codis.jodis.JedisResourcePool;
import io.codis.jodis.RoundRobinJedisPool;
import redis.clients.jedis.Jedis;

/**
 * @author knightliao
 * @date 2016/12/12 11:31
 */
public class JodisExample {

    public static void main(String[] args) {

        JedisResourcePool jedisPool = RoundRobinJedisPool.create()
                .curatorClient("127.0.0.1:8481", 30000).zkProxyDir("/zk/codis/db_lang_test01/proxy").build();

        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set("foo", "bar");
            String value = jedis.get("foo");
            System.out.println(value);
        }
    }
}
