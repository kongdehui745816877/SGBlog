package com.kong.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.kong.domain.ResponseResult;
import com.kong.domain.entity.Category;
import com.kong.domain.vo.CategoryVo;
import com.kong.domain.vo.ExcelCategoryVo;
import com.kong.enums.AppHttpCodeEnum;
import com.kong.service.CategoryService;
import com.kong.utils.BeanCopyUtils;
import com.kong.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/content/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询全部分类
     * @return
     */
    @RequestMapping("/listAllCategory")
    public ResponseResult listAllCategory(){
        List<CategoryVo> list = categoryService.listAllCategory();
        return ResponseResult.okResult(list);
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param category
     * @return
     */
    @GetMapping("/list")
    public ResponseResult getList(Integer pageNum, Integer pageSize, Category category){
        return categoryService.getList(pageNum,pageSize,category);
    }

    /**
     * 添加分类
     * @param category
     * @return
     */
    @PostMapping
    public ResponseResult add(@RequestBody Category category){
        categoryService.save(category);
        return ResponseResult.okResult();
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseResult getInfo(@PathVariable(value = "id")Long id){
        Category category = categoryService.getById(id);
        return ResponseResult.okResult(category);
    }
    /**
     * 修改分类
     * @param category
     * @return
     */
    @PutMapping
    public ResponseResult edit(@RequestBody Category category){
        categoryService.updateById(category);
        return ResponseResult.okResult();
    }

    /**
     * 删除分类
     * @param ids
     * @return
     */
    @DeleteMapping( "/{ids}")
    public ResponseResult remove(@PathVariable List<Long> ids){
        categoryService.removeByIds(ids);
        return ResponseResult.okResult();
    }


    /**
     * 导出分类
     * @param response
     */
    @PreAuthorize("@ps.hasPermissions('content:category:export')")
    @GetMapping("/export")
    public void export(HttpServletResponse response){
        try {
            //设置下载文件的请求头
            WebUtils.setDownLoadHeader("分类.xlsx",response);
            //获取需要导出的数据
            List<Category> categoryVos = categoryService.list();

            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBeanList(categoryVos, ExcelCategoryVo.class);

            //把数据写入Excel中
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE).sheet("分类导出")
                    .doWrite(excelCategoryVos);
        } catch (Exception e) {
            e.printStackTrace();
            //如果出现异常也要响应JSON
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }

}
