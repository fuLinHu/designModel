package com.forest.tiger.carm.service;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.WebcamUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/12/30 9:53
 * @Version V1.0
 */
public class CarmService {
    public String takePictures(){
        Webcam webcam = Webcam.getDefault();
        if (webcam == null) {
            return "没有找到摄像设备！";
        }
        boolean open = webcam.open();
        return "";
    }

    public static void main(String[] args) {
        Webcam webcam = Webcam.getDefault();
        if (webcam != null) {
            String filePath =  "F:/picture/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            File path = new File(filePath);
            if (!path.exists()) {//如果文件不存在，则创建该目录
                path.mkdirs();
            }
            String time = new SimpleDateFormat("yyyMMdd_HHmmss").format(new Date());
            File file = new File(filePath + "/" + time + ".jpg");
            webcam.setViewSize(WebcamResolution.VGA.getSize());
            WebcamUtils.capture(webcam, file);
        }


    }
}
