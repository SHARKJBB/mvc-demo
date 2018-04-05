package com.JBB.tedu.test;

import com.JBB.tedu.dbutil.HuiBaoDBUtil;
import com.JBB.tedu.dbutil.UserDBUtil;
import com.JBB.tedu.entity.HuiBao;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class Demo {
    public static void main(String[] args) {
//        UserDBUtil userDBUtil = new UserDBUtil();
////        userDBUtil
        HuiBaoDBUtil huiBaoDBUtil = new HuiBaoDBUtil();
//        HuiBao hbShow = new HuiBao();
//        HuiBao hb = HuiBao.newHuiBao(3, "1", 3, "11", "11", "11", 1, 1);
//        huiBaoDBUtil.insertHuiBao(hb);
//        HuiBao hbShow = huiBaoDBUtil.findHuiBaoByID(6);
//        System.out.println(hbShow.toString());
//        huiBaoDBUtil.findHuiBaoByUseridAndTY(1, 1);
//        System.out.println(hbShow.toString());
//        huiBaoDBUtil.findHuiBaoByUseridAndST(1, 1);
//        System.out.println(hbShow.toString());
        ArrayList<HuiBao> hbs = new ArrayList<>();
        huiBaoDBUtil.findHuiBaoByUseridAndTYAndST(1, 1, 1);
        for (HuiBao hbShow : hbs) {
            System.out.println(hbShow.toString());
        }

    }
}
