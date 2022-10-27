package com.kong.controller;

import com.kong.domain.ResponseResult;
import com.kong.domain.entity.Link;
import com.kong.domain.vo.LinkManagerVo;
import com.kong.domain.vo.PageVo;
import com.kong.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/content/link")
@RestController
public class LinkController {

    @Autowired
    private LinkService linkService;

    /**
     * 友链分页查询
     * @param pageNum
     * @param pageSize
     * @param link
     * @return
     */
    @GetMapping("/list")
    public ResponseResult getAllLink(Integer pageNum, Integer pageSize, Link link){
        PageVo pageVo = linkService.selectLinkPage(pageNum,pageSize,link);
        return ResponseResult.okResult(pageVo);
    }

    /**
     * 新增友链
     * @param link
     * @return
     */
    @PostMapping
    public ResponseResult add(@RequestBody Link link){
        linkService.save(link);
        return ResponseResult.okResult();
    }

    /**
     * 删除友链
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable List<Long> id){
        linkService.removeByIds(id);
        return ResponseResult.okResult();
    }

    /**
     * 修改友链
     * @param link
     * @return
     */
    @PutMapping
    public ResponseResult edit(@RequestBody Link link){
        linkService.updateById(link);
        return ResponseResult.okResult();
    }

    /**
     * 查询友链
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseResult getInfo(@PathVariable(value = "id")Long id){
        Link link = linkService.getById(id);
        return ResponseResult.okResult(link);
    }

    /**
     * 修改友链状态
     * @param link
     * @return
     */
    @PutMapping("/changeLinkStatus")
    public ResponseResult changeLinkStatus(@RequestBody Link link){
        linkService.updateById(link);
        return ResponseResult.okResult();
    }




}
