package com.kong.constants;

public class SystemConstants
{
    /**
     *  文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     *  文章是正常分布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;

    /*
    * 页码
    * */
    public static final int Current = 1;

    /**
     * 页大小
     */
    public static final int Size = 10;

    /**
     * 分类正常
     */
    public static final String  STATUS_NORMAL = "0";
    /**
     * 友链状态为审核通过
     */
    public static final String  LINK_STATUS_NORMAL = "0";

    /**
     * 根评论
     */
    public static final int ROOT_ID=-1;

    /**
     * 评论类型为：文章评论
     */
    public static final String ARTICLE_COMMENT = "0";

    /**
     * 评论类型为：友联评论
     */
    public static final String LINK_COMMENT = "1";
    public static final String MENU = "C";
    public static final String BUTTON = "F";
    /** 正常状态 */
    public static final String NORMAL = "0";
    public static final String ADMAIN = "1";

    /**
     * 浏览量存储在redis的key
     */
    public static final String REDIS_KEY="article:viewCount";
}