# Version
V3.0

# Introduce
The eGroup AI face engine has ability to integrate with your AP.
The sample code demo how to use the face engine by java.

# Enviroment
* Wintel Enviroment
Deep Learning Architecture
Support Edge Computing Solution

* Wintel Enviroment (minimum)
1. Software：Windows 7／10、C++ Redistributable 2015 
2. Hardware：4th Generation Intel® Core™ i3 (SSE3) 
3. Input Source：
  	* Web Camera：
		*	Resolution：360p ~ 1080p
	* IP Camera
		*	Resolution：360p ~ 4K
		*	Protocol：RTSP、RTMP
		*	Format：H.264、H.265、M-JPEG
	* Video File
		*	Protocol：RTSP、RTMP
		*	Format：MP4、MKV、MOV、AVI、FLV
	* Image File
		*	Format：JPG、PNG

# Function
## Retrieve
Start the FaceRecognize engine and the engine will start to recognize the source you input, you can control cli to retrieve frame or face to your folder.

## Train 
You can use list.txt that contain face's path name face's name to train the model. 

## Model Switch
Switch your new model, e.g model1 is in used and you train the new model2 ,you can switch model2 don't need to restart the FaceRecognize engine.

## Model Merge
Merge your new model, e.g If you have model1 and model2, you can merge to model3

## GetCacheResult
If you want to real-time recognize ,you can find newest 100 record in the cache.result.json

## GetAllResult
If you want to get the all recognize result , you can find in the output.json
## Resource
* Official website : https://www.egroup.com.tw

* [Youtube](https://ppt.cc/f78xjx) : 

1. [Reference 2018 IoT Seminar FaceCheckIn【Case】](https://www.youtube.com/watch?v=sF6U7h4f9EQ)
2. [CMD Practice【CPU Demo】](https://www.youtube.com/watch?v=Am8SukUPVSc)
3. [Reference 2017 Taiwan Future Tech FaceCheckIn【Case】](https://www.youtube.com/watch?v=YdUSXfnOnAU)
4. [IoT Seminar FaceCheckIn【Case】](https://www.youtube.com/watch?v=sF6U7h4f9EQ)
5. [FaceCheckIn(JAVA GUI)【CPU Demo】](https://www.youtube.com/watch?v=9ZV8Jjqi5SY)
6. [Multiple WebCam Recognition with one PC【CPU Demo】](https://www.youtube.com/watch?v=OC5wpANob_A)
7. [Detect & Train at the same time【CPU Demo】](https://www.youtube.com/watch?v=g9Xg2OaepHw)
8. [Object Detection【GPU Demo】](https://www.youtube.com/watch?v=H6SP5UpD2wk)

* Github : https://github.com/eGroupTeam



【Contact Information】

Contact Person：Daniel 

Email：egroup.daniel@gmail.com

Powered By eGroup AI
