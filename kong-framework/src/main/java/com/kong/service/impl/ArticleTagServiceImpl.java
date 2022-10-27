package com.kong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kong.domain.entity.ArticleTag;
import com.kong.mapper.ArticleTagMapper;
import com.kong.service.ArticleTagService;
import org.springframework.stereotype.Service;

/**
 * 文章标签关联表(ArticleTag)表服务实现类
 *
 * @author makejava
 * @since 2022-10-19 15:16:50
 */
@Service("articleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}
