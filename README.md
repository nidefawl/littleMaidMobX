# littleMaidMobX
My own personal fork of Littlemaidmob

Will hopefully fix annoyances, improve mod compatability and add some new features.

### Extra notes for adding new models in this version
---
Every model class must override the following constructor
```java
public ModelClassNameHere(StringBuilder hack) {
	super(hack); // optional line
}
```
