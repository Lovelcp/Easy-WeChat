package com.wooyoo.wechat.http.common;

import java.util.List;

public class SyncKey {
    private Long Count;
    private List<KeyVal> List;

    public SyncKey() {
    }

    public Long getCount() {
        return Count;
    }

    public void setCount(Long count) {
        Count = count;
    }

    public java.util.List<KeyVal> getList() {
        return List;
    }

    public void setList(java.util.List<KeyVal> list) {
        List = list;
    }

    public static class KeyVal {
        private Long Key;
        private Long Val;

        public KeyVal() {
        }

        public Long getKey() {
            return Key;
        }

        public void setKey(Long key) {
            Key = key;
        }

        public Long getVal() {
            return Val;
        }

        public void setVal(Long val) {
            Val = val;
        }
    }
}
