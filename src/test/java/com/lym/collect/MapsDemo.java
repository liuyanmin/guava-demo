package com.lym.collect;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by liuyanmin on 2020/6/21.
 */
public class MapsDemo {

    /**
     * 1、List 转换成 Map
     * 注意：key不能有重复的，否则抛异常
     */
    @Test
    public void testUniqueIndex() {
        List<SiteInfo> sets = Lists.newArrayList(
                new SiteInfo("1001", "东直门"),
                new SiteInfo("1002", "西直门"));
        Map<String, SiteInfo> result = Maps.uniqueIndex(sets, SiteInfo::getSiteId);
        System.out.println(result);//{1001=SiteInfo{siteId='1001', siteName='东直门'}, 1002=SiteInfo{siteId='1002', siteName='西直门'}}
    }

    /**
     * 2、map1 中去掉与 map2 相同的key
     */
    @Test
    public void testDifferenceOnlyOnLeft() {
        Map<String, String> map1 = Maps.newHashMap();
        map1.put("k1", "v1");
        map1.put("k2", "v2");
        map1.put("k3", "v3");
        Map<String, String> map2 = Maps.newHashMap();
        map2.put("k1", "v1");
        map2.put("k3", "v333");
        MapDifference<String, String> result = Maps.difference(map1, map2);
        System.out.println(result.entriesOnlyOnLeft());//{k2=v2}
    }

    /**
     * 3、map2 中去掉与 map1 相同的key
     */
    @Test
    public void testDifferenceOnlyOnRight() {
        Map<String, String> map1 = Maps.newHashMap();
        map1.put("k1", "v1");
        map1.put("k2", "v2");
        map1.put("k3", "v3");
        Map<String, String> map2 = Maps.newHashMap();
        map2.put("k1", "v1");
        map2.put("k3", "v333");
        map2.put("k4", "v4");
        MapDifference<String, String> result = Maps.difference(map1, map2);
        System.out.println(result.entriesOnlyOnRight());//{k4=v4}
    }

    /**
     * 4、Map<key, value1> 转变为 Map<key, value2>
     *  或者说，把 value1 的值转换成 value2
     */
    @Test
    public void testTransformValues() {
        Map<String, SiteInfo> map = Maps.newHashMap();
        map.put("1001", new SiteInfo("1001", "东直门"));
        map.put("1002", new SiteInfo("1002", "西直门"));
        Map<String, String> result = Maps.transformValues(map, SiteInfo::getSiteName);
        System.out.println(result);//{1002=西直门, 1001=东直门}
    }

    /**
     * 5、过滤 Map 的 key
     */
    @Test
    public void testFilterKeys() {
        Map<String, SiteInfo> map = Maps.newHashMap();
        map.put("1001", new SiteInfo("1001", "东直门"));
        map.put("1002", new SiteInfo("1002", "西直门"));
        map.put("1003", new SiteInfo("1003", "望京"));
        Map<String, SiteInfo> result = Maps.filterKeys(map, key -> "1003".equals(key));
        System.out.println(result);//{1003=SiteInfo{siteId='1003', siteName='望京'}}
    }

    /**
     * 6、过滤 Map 的 value
     */
    @Test
    public void testFilterValues() {
        Map<String, SiteInfo> map = Maps.newHashMap();
        map.put("1001", new SiteInfo("1001", "东直门"));
        map.put("1002", new SiteInfo("1002", "西直门"));
        map.put("1003", new SiteInfo("1003", "望京"));
        Map<String, SiteInfo> result = Maps.filterValues(map, value -> "1001".equals(value.getSiteId()) );
        System.out.println(result);//{1001=SiteInfo{siteId='1001', siteName='东直门'}}
    }

    /**
     * 7、通过List获取对象，并存入Map中
     * 使用场景：ids -> 数据库 -> Map<id,对象>
     */
    @Test
    public void testToMap() {
        List<String> keys = Lists.newArrayList("1001", "1002");
        Map<String, SiteInfo> immutableMap = Maps.toMap(keys, key -> new SiteInfo(key, "站点"+key));
        System.out.println(immutableMap);
    }

    /**
     * 8、Properties 转 Map
     */
    @Test
    public void testFromProperties() {
        Properties properties = new Properties();
        properties.setProperty("host", "127.0.0.1");
        properties.setProperty("port", "3306");
        Map<String, String> result = Maps.fromProperties(properties);
        System.out.println(result);
    }


    class SiteInfo {
        private String siteId;
        private String siteName;

        public SiteInfo(String siteId, String siteName) {
            this.siteId = siteId;
            this.siteName = siteName;
        }

        public SiteInfo() {
        }

        public String getSiteId() {
            return siteId;
        }

        public void setSiteId(String siteId) {
            this.siteId = siteId;
        }

        public String getSiteName() {
            return siteName;
        }

        public void setSiteName(String siteName) {
            this.siteName = siteName;
        }

        @Override
        public String toString() {
            return "SiteInfo{" +
                    "siteId='" + siteId + '\'' +
                    ", siteName='" + siteName + '\'' +
                    '}';
        }
    }
}
