package com.bigdata.hadoop.Maxtemp.SecondarySort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * Created by daoyao on 2019/4/1 18:53
 */
public class ComboKeyComparator extends WritableComparator {
    protected ComboKeyComparator() {
        super(ComboKey.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        System.out.println("ComboKeyComparator" + a + "," + b);
        ComboKey k1 = (ComboKey) a;
        ComboKey k2 = (ComboKey) b;
        return k1.compareTo(k2);
    }
}
