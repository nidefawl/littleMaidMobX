# littleMaidMobX
My own personal fork of Littlemaidmob

Will hopefully fix annoyances, improve mod compatability and add some new features.

Downloads

[1.7.10 (1.3.2)](https://www.dropbox.com/s/cgdmkeaqpzhlcbl/LittleMaidMobEnhanced-1.7.10-1.3.2.jar?dl=0)

1.8.9 is now in the works stay tuned.

### Extra notes for adding new models in this version
---
Every model class must override the following constructor
```java
public ModelClassNameHere(StringBuilder hack) {
	super(hack); // optional line
}
```
