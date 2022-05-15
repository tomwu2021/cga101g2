package connection;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {
	// Singleton單例模式
	private static JedisPool pool = null;

	private JedisUtil() {
	}

	public static JedisPool getJedisPool() {
		// double lock
		if (pool == null) {
			synchronized(JedisUtil.class) {
				if (pool == null) {
					JedisPoolConfig config = new JedisPoolConfig();
					config.setMaxTotal(8);
					config.setMaxIdle(8);
					config.setMaxWaitMillis(10000);
					pool = new JedisPool(config, "localhost", 6379);
					System.out.println("JedisUtil JedisPool getJedisPool() 隨著伺服器啟動的JedisPool,理論上只能印一次");
				}
			}
		}
		return pool;
	}

	public static void shutdownJedisPool() {
		if (pool != null)
			pool.destroy();
		System.out.println("JedisUtil void shutdownJedisPool() 隨著伺服器關閉的JedisPool,理論上只能印一次");
	}

}
