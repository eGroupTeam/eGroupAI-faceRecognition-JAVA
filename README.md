# Version V3.2.1(2019/08/29 update)

# [Introduce](https://www.egroup.com.tw/documentation/introduce?type=cpu&version=latest)
System Structure
## Engine Core 
* Training：Building a face model library.
* Recognition：Image input, recognition, and results. 
* Model Switch：Face model library is instantly switched in the recognition process. 
* Model Append：Face model library is appended. 
* Model Insert：Face model library is stored in the recognition process, and the result is inserted online to instantly recognize the new result.

## Application Integration
### Flow Description
1. The application controls the training engine.
2. Training engine generates a face model library.
3. The application controls the recognition engine, which reads the face model library for recognition
4. Recognition engine generates recognition result in JSON Format.
5. The application reads the recognition result and applies it.
### [Flow chart](https://www.egroup.com.tw/documentation/introduce?type=cpu&version=latest#6d417a0b5716)

# Enviroment
## [Recommended system specifications](https://www.egroup.com.tw/documentation/introduce?type=cpu&version=latest#5efa8b707cfb7d71898f683c)
## [Software installation](https://www.egroup.com.tw/documentation/introduce?type=cpu&version=latest#8edf9ad45b8988dd)
## [Input Source](https://www.egroup.com.tw/documentation/introduce?type=cpu&version=latest#8f3851655f7150cf4f866e90)
## [General Precautions](https://www.egroup.com.tw/documentation/introduce?type=cpu&version=latest#6ce8610f4e8b9805)

# Function
## [Recognize](https://www.egroup.com.tw/documentation/recognize?type=cpu&version=latest#6d417a0b5716)

## [Train](https://www.egroup.com.tw/documentation/train?type=cpu&version=latest#6d417a0b5716) 

## [Model Switch](https://www.egroup.com.tw/documentation/model-switch?type=cpu&version=latest#4f7f752860c55883)
Replace the file content (new face model library path) according to the model, perform the model replacement, compare it instantly, and return the latest recognition result.

## [Model Append](https://www.egroup.com.tw/documentation/model-merge?type=cpu&version=latest#4f7f752860c55883)
Store/O2O Edge Computing Application
* The customer only needs to build a face model library in one of the branches. When the customer goes to a different branch, they can be directly recognized, and the customer's face model library does not need to be reestablished.

## [Model Insert](https://www.egroup.com.tw/documentation/model-insert?type=cpu&version=latest#4f7f752860c55883)
In the case of large-scale face model library, on-site real-time training, on-site instant recognition
 When the face model library reaches a certain size, the model will become more difficult to replace. The training of instant recognition will also become more difficult. Model insert can be used at this point to add several trained face model libraries from online. There is no need to uninstall the old model, so that the actual face model library file is not generated. This way, the time for model switch can be saved, and the effect of instant recognition can be further achieved. However, because it is an online insert, it means that when the recognition engine stops working and restarts, the recognition result will be reverted to the model before the online insert, so it is recommended to use the scheduling method to append the face model library.(Model Append)

## [GetCacheResult](https://www.egroup.com.tw/documentation/recognize?type=cpu&version=latest#8fa88b580020004f00750074007000750074)
If you want to real-time recognize ,you can find newest 100 record in the cache.result.json

## [GetAllResult](https://www.egroup.com.tw/documentation/recognize?type=cpu&version=latest#8fa88b580020004f00750074007000750074)
If you want to get the all recognize result , you can find in the output.json
## Resource
* [Document](https://www.egroup.com.tw/documentation/introduce?type=cpu&version=latest#7cfb7d7167b669cb) : https://www.egroup.com.tw/documentation/introduce?type=cpu&version=latest#7cfb7d7167b669cb

* [Official website](https://www.egroup.com.tw) : https://www.egroup.com.tw

* [Youtube](https://ppt.cc/f78xjx) : 
1. [Taiwan Social Workers Association Annual Conference Activity](https://www.youtube.com/watch?v=wupwnKb7RyY)
2. [Visitor management system and face recognition guard system](https://www.youtube.com/watch?v=KH2IDPDulDQ&t=24s)
3. [Reference 2018 IoT Seminar FaceCheckIn【Case】](https://www.youtube.com/watch?v=sF6U7h4f9EQ)
4. [Reference 2017 Taiwan Future Tech FaceCheckIn【Case】](https://www.youtube.com/watch?v=YdUSXfnOnAU)
5. [CMD Practice【CPU Demo】](https://www.youtube.com/watch?v=Am8SukUPVSc)
6. [FaceCheckIn(JAVA GUI)【CPU Demo】](https://www.youtube.com/watch?v=9ZV8Jjqi5SY)
7. [Multiple WebCam Recognition with one PC【CPU Demo】](https://www.youtube.com/watch?v=OC5wpANob_A)
8. [Detect & Train at the same time【CPU Demo】](https://www.youtube.com/watch?v=g9Xg2OaepHw)
9. [Object Detection【GPU Demo】](https://www.youtube.com/watch?v=H6SP5UpD2wk)

* Github : https://github.com/eGroupTeam



【Contact Information】

Contact Person：Daniel 

Email：egroup.daniel@gmail.com

Powered By eGroupAI
