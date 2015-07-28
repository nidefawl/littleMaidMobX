# littleMaidMobX
Personal Fork of Littlemaidmob

Download for the lazy people:
https://www.dropbox.com/s/7hjb9ixhfzqkudb/LMMX-kkpl.jar?dl=0

I'd still suggest everyone to build it themselves.

### Extra notes for adding new models in this version
---
Every model class must override the following constructor
```java
public ModelClassNameHere(StringBuilder hack) {
	super(hack); // optional line
}
```
