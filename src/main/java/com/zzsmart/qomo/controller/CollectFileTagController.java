package com.zzsmart.qomo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import com.zzsmart.qomo.entity.CollectFileTag;
import com.zzsmart.qomo.service.CollectFileTagService;
import org.jeecg.common.system.base.controller.JeecgController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 采集文件标签 前端控制器
 * </p>
 *
 * @author WangBo
 * @since 2024-04-20
 */
@Slf4j
@Api(tags = "采集文件标签")
@RestController
@RequiredArgsConstructor
@RequestMapping("/collectFileTag")
public class CollectFileTagController extends JeecgController<CollectFileTag, CollectFileTagService> {

    private final CollectFileTagService collectFileTagService;

    /**
     * 分页列表查询
     */
    @ApiOperation(value="采集文件标签-分页列表查询", notes="采集文件标签-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<CollectFileTag>> queryPageList(CollectFileTag collectFileTag,
                                                                @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                                @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                                HttpServletRequest req) {
        QueryWrapper<CollectFileTag> queryWrapper = QueryGenerator.initQueryWrapper(collectFileTag, req.getParameterMap());
        Page<CollectFileTag> page = new Page<>(pageNo, pageSize);
        IPage<CollectFileTag> pageList = collectFileTagService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

    /**
     *  添加
     */
    @AutoLog(value = "采集文件标签-添加")
    @ApiOperation(value="采集文件标签-添加", notes="采集文件标签-添加")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody CollectFileTag collectFileTag) {
        collectFileTagService.save(collectFileTag);
		return Result.OK("添加成功！");
	}

    /**
     *  编辑
     */
    @AutoLog(value = "采集文件标签-编辑")
    @ApiOperation(value="采集文件标签-编辑", notes="采集文件标签-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
    public Result<String> edit(@RequestBody CollectFileTag collectFileTag) {
        collectFileTagService.updateById(collectFileTag);
		return Result.OK("编辑成功!");
	}

    /**
     *  通过id删除
     */
    @AutoLog(value = "采集文件标签-通过id删除")
    @ApiOperation(value="采集文件标签-通过id删除", notes="采集文件标签-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name="id") String id) {
        collectFileTagService.removeById(id);
		return Result.OK("删除成功!");
	}

    /**
     *  批量删除
     */
    @AutoLog(value = "采集文件标签-批量删除")
    @ApiOperation(value="采集文件标签-批量删除", notes="采集文件标签-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name="ids") String ids) {
        collectFileTagService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
	}

    /**
     * 通过id查询
     */
    @ApiOperation(value="采集文件标签-通过id查询", notes="采集文件标签-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<CollectFileTag> queryById(@RequestParam(name="id") String id) {
        CollectFileTag collectFileTag = collectFileTagService.getById(id);
		if(collectFileTag==null) {
            return Result.error("未找到对应数据");
		}
        return Result.OK(collectFileTag);
	}

    /**
     * 导出excel
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CollectFileTag collectFileTag) {
        return super.exportXls(request, collectFileTag, CollectFileTag.class, "采集文件标签");
    }

    /**
     * 通过excel导入数据
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, CollectFileTag.class);
    }
}
