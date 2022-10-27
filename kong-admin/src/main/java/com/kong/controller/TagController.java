package com.kong.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kong.domain.ResponseResult;
import com.kong.domain.dto.EditTagDto;
import com.kong.domain.dto.TagListDao;

import com.kong.domain.entity.Tag;
import com.kong.domain.vo.PageVo;
import com.kong.domain.vo.TagVo;
import com.kong.service.TagService;
import com.kong.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;


    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDao tagListDao){
        return tagService.pageTagList(pageNum,pageSize,tagListDao);
    }

    @PostMapping
    public ResponseResult addTag(@RequestBody TagVo tagVo){
        Tag tag = BeanCopyUtils.copyBean(tagVo, Tag.class);
        tagService.save(tag);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{ids}")
    public ResponseResult deleteTag(@PathVariable Long[] ids){
        for (Long id : ids) {
            tagService.removeById(id);
        }
        return ResponseResult.okResult();
    }

    @GetMapping("/{id}")
    public ResponseResult getTagById(@PathVariable Long id){
        Tag tag = tagService.getById(id);
        return ResponseResult.okResult(tag);
    }

    @PutMapping
    public ResponseResult updateTag(@RequestBody EditTagDto editTagDto){
        Tag tag = BeanCopyUtils.copyBean(editTagDto, Tag.class);
        tagService.updateById(tag);
        return ResponseResult.okResult();
    }

    @GetMapping("/listAllTag")
    public ResponseResult<TagVo> listAllTag(){
        return tagService.listAllTag();
    }


}
