# littleMaidMobX
My own personal fork of Littlemaidmob

Will hopefully fix annoyances, improve mod compatability and add some new features.

Anyone looking for a download cant find it here: https://www.dropbox.com/s/x1lsv4sbiec7r12/LittleMaidMobEnhanced-1.7.10-1.3.jar?dl=0

### Extra notes for adding new models in this version
---
Every model class must override the following constructor
```java
public ModelClassNameHere(StringBuilder hack) {
	super(hack); // optional line
}
```
