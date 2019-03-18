package com.chemguan.business.core.controller;


import com.chemguan.business.core.results.Result;
import com.chemguan.business.core.results.ResultGenerator;
import com.chemguan.util.fileio.FileUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class FileController {

    @Value("${fileinfo.imgRoot}")
    private String imgRoot;

    /*
     * 统一图片上传util
     */
    @PostMapping("uploadfile")
    @ApiOperation("上传图片")
    public Result uploadfile(HttpServletRequest request){
        Result res=null;
        String base64bob = request.getParameter("base64bob");
        String filename = request.getParameter("filename");
        FileUtil fileUtil=new FileUtil();
        String imgpath = fileUtil.saveImageBase64(imgRoot,base64bob,filename);
        if(StringUtils.isEmpty(imgpath)){
            res = ResultGenerator.genFailResult("上传失败");
        }else{
            res = ResultGenerator.genSuccessResult(imgpath);
        }
        return res;
    }



    /**
     * 文件下载
     * @return
     */
    @RequestMapping("download")
    public void findall(HttpServletResponse response, String fileName) throws Exception {
        FileUtil util=new FileUtil();
        String filepath=imgRoot+fileName;
        util.downLoad(filepath,response,true);
    }
}
