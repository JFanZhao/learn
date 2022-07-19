package org.ivan.learn.ds.sort;

import java.util.Arrays;

/**
 * 冒泡排序优化版本2：当已经有序了之后，不需要再继续进行比较
 * 加入标志位，判断
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−10-28 21:45
 **/
public class BubbleSort2 {
    public static void sort(int arrry[]) {
        for (int i = 0; i < arrry.length - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < arrry.length - i - 1; j++) {
                int tmp = 0;
                if (arrry[j] > arrry[j + 1]) {
                    //交换
                    tmp = arrry[j];
                    arrry[j] = arrry[j+1];
                    arrry[j + 1] = tmp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    public static void main(String[] args) {
//        int[] array = new int[]{5,8,6,3,9,2,1,7};
//        sort(array);
//        System.out.println(Arrays.toString(array));

        String str ="深圳配送中心,深圳市;惠州市;河源市;汕尾市\n" +
                "广州配送中心,广州市;佛山市;肇庆市;清远市;云浮市\n" +
                "粤西配送中心,珠海市;中山市;江门市;湛江市;茂名市;阳江市\n" +
                "福建配送中心,厦门市;泉州市;漳州市;福州市;龙岩市\n" +
                "海南配送中心,海口市;三亚市;琼海市;儋州市;文昌市;万宁市;陵水黎族自治县;昌江黎族自治县; 东方市;澄迈县\n" +
                "昆明配送中心,昆明市;曲靖市\n" +
                "南宁配送中心,南宁市;百色市;柳州市\n" +
                "汕头配送中心,汕头市;梅州市;揭阳市;潮州市\n" +
                "东莞配送中心,东莞市\n" +
                "长沙配送中心,邵阳市;长沙市;常德市;衡阳市;娄底市;湘潭市;株洲市\n" +
                "上海配送中心,上海市;苏州市;无锡市;南通市\n" +
                "南京配送中心,南京市;镇江市;马鞍山市;扬州市;泰州市;滁州市;常州市\n" +
                "杭州配送中心,杭州市;湖州市\n" +
                "宁波配送中心,宁波市\n" +
                "合肥配送中心,合肥市;芜湖市;安庆市;六安市\n" +
                "金华配送中心,金华市;绍兴市\n" +
                "徐州配送中心,徐州市;临沂市;盐城市;宿州市;淮安市\n" +
                "青岛配送中心,青岛市;潍坊市;烟台市\n" +
                "南昌配送中心,南昌市;九江市;上饶市;宜春市;抚州市;景德镇市;丰城市;新余市;鄱阳县;鹰潭市;萍乡市\n" +
                "济南配送中心,济南市;淄博市;聊城市;泰安市\n" +
                "北京配送中心,北京市;天津市;唐山市\n" +
                "武汉配送中心,武汉市;黄石市;鄂州市;咸宁市;信阳市;黄冈市;仙桃市\n" +
                "郑州配送中心,郑州市;洛阳市;驻马店市;安阳市;平顶山市;新乡市;鹤壁市;漯河市;许昌市\n" +
                "太原配送中心,太原市;晋中市;长治市;晋城市\n" +
                "重庆配送中心,重庆市;泸州市\n" +
                "成都配送中心,成都市;绵阳市;乐山市;眉山市;遂宁市;\n" +
                "西安配送中心,西安市;商洛市\n" +
                "宜昌配送中心,宜昌市;荆州市;襄阳市\n" +
                "石家庄配送中心,石家庄市;保定市\n" +
                "贵阳配送中心,贵阳市;凯里市;遵义市";

        String[] split = str.split("\n");
        String format ="when cityName in('深圳市','惠州市','河源市','汕尾市') then '深圳配送中心'";
        for (String s :
                split) {
            String[] ss = s.split(",");
            String area =ss[0];
            String[] cs = ss[1].split(";");
            String cities="";
            for (int i=0;i<cs.length;i++){
//                cities = cities+ "'"+cs[i]+"'";
//                if (i != cs.length -1){
//                    cities+=",";
//                }
                System.out.println(cs[i]+","+area);
            }
//            String format1 = String.format("when cityName in(%s) then '%s'", cities, area);
//            System.out.println(format1);


        }
    }
}
