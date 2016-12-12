package com.github.knightliao.canalx.plugin.processor.kv.data;

/**
 * @author knightliao
 * @date 2016/12/12 19:01
 */
public enum StoreType {

    KV("kv"), CODIS("codis");
    private String name;

    StoreType(String name) {
        this.name = name;
    }

    public static StoreType get(String name) {
        if (null == name) {
            return null;
        }
        for (StoreType temp : StoreType.values()) {
            if (temp.getName().equals(name)) {
                return temp;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
