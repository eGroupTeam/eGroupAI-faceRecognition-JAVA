# Engine Version V4.2.2(2020/11/20 update)
* This is the JAVA project , below is the other language quickStart
  * [QuickStart-C#](https://github.com/eGroupTeam/eGroupAI-faceRecognition-CSharp)
  * [QuickStart-Python](https://github.com/eGroupTeam/eGroupAI-faceRecognition-Python)

# QuickStart
* [QuickStart Document](https://reurl.cc/Y6r9N4)
* [eGroupAI【Tutorial】│ Face Recognition SDK - Quick Start by JAVA](https://reurl.cc/Z759b3)
   * Clone Project
   * Copy project env into project
   * Import Project
   * Create the folder at disk c name QuickStart and copy engine,license and resources into the folder
      * Download engine and put in the QuickStart ( [Login](https://www.egroup.com.tw) )
          * e.g. C:\QuickStart_bk\eGroupAI_FaceEngine_CPU_Windows_V4.2.2
      * Download license.key and put int the engine foler( [Login](https://www.egroup.com.tw) )
          * e.g. C:\QuickStart\eGroupAI_FaceEngine_CPU_Windows_V4.2.2\license.key
      * Download QuickStart resources and put in the QuickStart 
          * e.g. C:\QuickStart\eGroupAI_FaceEngine_CPU_Windows_V4.2.2\resources
   * Run Java application - QuickStart Example
      * Application will call engine by cli
          * Training Jerry faceDB
          * Start 1st recognition - video
          * Training Leonard faceDB
          * Model Insert Leonard faceDB
          * Get 1st recognition result
          * Training Daniel faceDB and Append all person faceDB to all.faceDB
          * Start 2nd recognition - video
          * Get 2nd recognition result

# [Document](https://www.egroup.com.tw/en/docs/windows-cpu/v4.2.1/introduce)
## Features
* [Train](https://www.egroup.com.tw/en/docs/windows-cpu/v4.2.1/train#0054007200610069006e0069006e0067002000500072006f006300650064007500720065)：Input specific face, Create a dedicated face model.
* [Recognition](https://www.egroup.com.tw/en/docs/windows-cpu/v4.2.1/recognize#005200650063006f0067006e006900740069006f006e)：Input Face, Recognized with face model and get result.
* [Model Insert](https://www.egroup.com.tw/en/docs/windows-cpu/v4.2.1/model-insert#004d006f00640065006c00200049006e0073006500720074)：Input untrained face and Trained immediately, Insert the new face model to face model
and get result at the same time.
* [Model Append](https://www.egroup.com.tw/en/docs/windows-cpu/v4.2.1/model-append#004d006f00640065006c00200041007000700065006e0064)：Append different face model into one.
* [Model Switch](https://www.egroup.com.tw/en/docs/windows-cpu/v4.2.1/model-switch#004d006f00640065006c0020005300770069007400630068)：Switch to the new face model and get result at the same time.

## Integration
### Procedure
1. Using Application software trigger [Train](https://www.egroup.com.tw/en/docs/windows-cpu/v4.2.1/model-switch#004d006f00640065006c0020005300770069007400630068) to Create Face Model.
2. Application software read image file and trigger [Recognition](https://www.egroup.com.tw/en/docs/windows-cpu/v4.2.1/recognize#005200650063006f0067006e006900740069006f006e) to do comparison with Face Model.
3. Output Comparison [Result(JSON format)](https://www.egroup.com.tw/en/docs/windows-cpu/v4.2.1/recognize#0052006500730075006c00740028004a0053004f004e00200066006f0072006d006100740029) to Application software.
### [Flow chart](https://www.egroup.com.tw/en/docs/windows-cpu/v4.2.1/introduce)

# Environments Required
## [Hardware and OS](https://www.egroup.com.tw/en/docs/windows-cpu/v4.2.1/introduce#0048006100720064007700610072006500200061006e00640020004f0053)
## [Input Devices And Protocol](https://www.egroup.com.tw/en/docs/windows-cpu/v4.2.1/introduce#0049006e0070007500740020004400650076006900630065007300200041006e0064002000500072006f0074006f0063006f006c)
## [Precautions](https://www.egroup.com.tw/en/docs/windows-cpu/v4.2.1/introduce#00500072006500630061007500740069006f006e0073)

# Function
## [Training Procedure](https://www.egroup.com.tw/en/docs/windows-cpu/v4.2.1/train#0046006c006f007700630068006100720074)

## [Recognition Procedure](https://www.egroup.com.tw/en/docs/windows-cpu/v4.2.1/recognize#0046006c006f007700630068006100720074) 
When the recognized face model is updated (modified or deleted) , you can use Model Switch to recognize the new face model in time and get the latest recognition results.

## [Model Switch Procedure](https://www.egroup.com.tw/en/docs/windows-cpu/v4.2.1/model-switch#004d006f00640065006c0020005300770069007400630068002000500072006f006300650064007500720065)
When the recognized face model is updated (modified or deleted) , you can use Model Switch to recognize the new face model in time and get the latest recognition results.

## [Model Append Procedure](https://www.egroup.com.tw/en/docs/windows-cpu/v4.2.1/model-append#005300630065006e006100720069006f0073)
Append different Face Model into one.

## [Model Insert Procedure](https://www.egroup.com.tw/en/docs/windows-cpu/v4.2.1/model-insert#005300630065006e006100720069006f0073)
Input untrained Face and Trained immediately, Insert the new Face Model to Face Model and get Result at the same time.

## GetResult
[Output JSON](https://www.egroup.com.tw/en/docs/windows-cpu/v4.2.1/qa#004a0073006f006e0020004900730073007500650073)

[Recognition Output](https://www.egroup.com.tw/en/docs/windows-cpu/v4.2.1/qa#004a0073006f006e0020004900730073007500650073)
*  Cache JSON file
    * Lighter JSON file (Newest 100 data)
* JSON file
    * Recognize result with Date

## Resource
* [Document](https://www.egroup.com.tw/en/docs)

* [Official website](https://www.egroup.com.tw) : https://www.egroup.com.tw
* [Youtube](https://www.youtube.com/channel/UCZ0S3b-P0v6gwU1YQifAXUA?view_as=subscriber) : 

  * [eGroupAI│【Knowledge】How to evaluate the face recognition engine-ROC Curves](https://www.youtube.com/watch?v=o6t3crMXqO4)  
  * [eGroupAI│【Partner】 DSI-Face recognition + face temperature detection intelligent epidemic prevention system](https://www.egroup.com.tw/en/solutions/1)
  * [eGroupAI│【Tutorial】Face Recognition SDK - Quick Start](https://www.youtube.com/watch?v=EAKXivFojF0)
  * [eGroupAI│【Tutorial】CMD Practice](https://www.youtube.com/watch?v=Am8SukUPVSc)
  * [MICEPass│【Test Report】LFW Testing](https://www.youtube.com/watch?v=SrBUcAlx8Po&t=193s)
  * [eGroupAI│【Demo】SaaS service - mobile facial recognition](https://www.egroup.com.tw/en/solutions/3)
  * [eGroupAI│【Demo】FaceGo recognition module](https://www.egroup.com.tw/en/solutions/4)
  * [MICEPass│【Demo】Activity face Check-In](https://www.egroup.com.tw/en/solutions/2)
  * [eGroupAI│【Demo】Taiwan Social Workers Association Annual Conference Activity](https://www.egroup.com.tw/en/solutions/5)
  * [eGroupAI│【Demo】Visitor management system and face recognition guard system](https://www.youtube.com/watch?v=KH2IDPDulDQ&t=24s)
  * [MICEPass│【Demo】Seminar Face Check-In/out](https://www.egroup.com.tw/en/solutions/6)
  * [eGroupAI│【Demo】Reference 2018 IoT Seminar FaceCheckIn](https://www.egroup.com.tw/en/solutions/7)
  * [eGroupAI│【Demo】Reference 2017 Taiwan Future Tech FaceCheckIn](https://www.egroup.com.tw/en/solutions/8)
  * [eGroupAI│【Demo】FaceCheckIn(JAVA GUI)](https://www.youtube.com/watch?v=9ZV8Jjqi5SY)
  * [eGroupAI│【Demo】Multiple WebCam Recognition with one PC](https://www.youtube.com/watch?v=OC5wpANob_A)
  * [eGroupAI│【Demo】Detect & Train at the same time](https://www.youtube.com/watch?v=g9Xg2OaepHw)  
  * [eGroupAI│【Demo】Object Detection](https://www.youtube.com/watch?v=H6SP5UpD2wk)





* Github : https://github.com/eGroupTeam



【Contact Information】
* Contact us for more information
    * Face recognition technology licensing
    Solutions
    Online discussion
* Daniel 886-2-2362-2508
* egroup.daniel@gmail.com
* eGroup co.,ltd
    * No. 47, Sec. 2, Xinhai Rd., Da’an Dist., Taipei City 106, Taiwan (R.O.C.)

Powered By eGroupAI
