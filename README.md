# horizontalListPicker

horizontalListPicker is one of android libs that helping to create horizotal picker with scaling and chanige alpha

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.



### Installing

A step by step series of examples that tell you have to get a development env running

Say what the step will be

```
allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
```

```
dependencies {
	        implementation 'com.github.ahmedshaban1:EasySlider:1.0.0'
	}
```




### And coding style tests

Explain what these tests test and why 
* xml code
```
  <com.ahmedshaban.horizontalpicker.HorizontalListPicker
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/horizontal_picker" />
```
* java code 
```
  horizontal_picker.setUp(this, new HorizontalListPickerAdapter(this, dummyList), 0);

        horizontal_picker.setOnItemSelectedListener(new PickerLayoutManager.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int position) {
                // selected index updated 
            }
        });
        
        
         // when you need to change alpha  defualt value .5f
         //horizontal_picker.setUnselectedAlpha(.2f);
         
         //when you need to change item scale 
         horizontal_picker.setScrollType(HorizontalListPicker.SCALETYPE);
         
         //to go next position call 
         //horizontal_picker.goNexT();
         
         // to call prevous position call 
          //horizontal_picker.goPrevious();
```



* Screenshots  

 ![alt text](https://github.com/ahmedshaban1/EasySlider/blob/master/Screenshot_2018-05-11-18-44-00.png)
 ![alt text](https://github.com/ahmedshaban1/EasySlider/blob/master/Screenshot_2018-05-11-18-44-06.png)
 ![alt text](https://github.com/ahmedshaban1/EasySlider/blob/master/Screenshot_2018-05-11-18-44-10.png)




## Authors

* **Ahmed Shaban** 

