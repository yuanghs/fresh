package com.fresh.util;

public class PetName {
    private static final String[] FIRST_NAME = {"幼稚的", "古怪的", "完美的", "记仇的", "机智的", "专业的",
    "暴走的", "美丽的", "结实的", "知名的", "热心的", "固执的", "诡异的", "繁忙的", "内向的", "狡猾的", "随和的",
    "开朗的", "坚强的", "脆弱的", "敏感的", "率直的", "迟钝的", "乐观的", "丧丧的", "可爱的", "体贴的", "安静的",
    "暴躁的", "倔强的", "冷淡的", "睿智的", "业余的", "挑剔的", "放飞自我的", "神经大条的", "一百个", "一个", "一只",
    "成千上万个"};

    private static final String[] LAST_NAME = {"汉堡", "薯条", "牛肉粒", "番茄", "生姜", "小饼干", "布丁",
    "袜子", "秋裤", "小棉袄", "小绵羊", "柴", "大橘", "小怪兽", "美少女", "凹凸曼", "唐僧", "金角大王", "银角大王",
    "葫芦娃", "阿童木", "孙悟空", "铁锤", "四眼仔", "萝卜头", "小胖几", "小僵尸", "美少年", "小老弟", "大兄弟",
    "大妹子", "大妈", "大爷", "怪叔叔", "怪阿姨", "小秃子","狗东西","小赤佬","猪头"};

    public static String create() {
        int first = (int)(Math.random() * 1000) % FIRST_NAME.length;
        String result = FIRST_NAME[first];
        int last = (int)(Math.random() * 1000) % LAST_NAME.length;
        result += LAST_NAME[last];
        return result;
    }

    public static void main(String[] args) {
        System.out.println(create());
    }
}
