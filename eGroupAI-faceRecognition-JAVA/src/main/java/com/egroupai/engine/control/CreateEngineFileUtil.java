package com.egroupai.engine.control;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.egroup.util.TxtUtil;
import com.egroup.util.TxtUtil.Charsets;
import com.egroup.util.UUIDGenerator;
import com.egroupai.engine.entity.TrainFace;

/**
 * @author daniel
 * @date 2020年11月24日 上午10:00:01
 * @version
 * @description:
 */
public class CreateEngineFileUtil {

  public boolean createTrainFaceTxt(String trainListPath, List<TrainFace> trainFaceList) {
    // init func
    final UUIDGenerator uuidGenerator = new UUIDGenerator();
    final TxtUtil txtUtil = new TxtUtil();
    boolean flag = false;
    // Create training
    final List<String> dataList = new ArrayList<>();
    for (TrainFace getTrainFace : trainFaceList) {
      for (String imagePath : getTrainFace.getImagePathList()) {
        dataList.add(imagePath + "\t" + getTrainFace.getPersonId() + "[No]" + uuidGenerator.getUUID());
      }
    }
    txtUtil.create(trainListPath, dataList, Charsets.BIG5);
    if (new File(trainListPath).exists()) {
      flag = true;
    }
    return flag;
  }
}
