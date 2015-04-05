package littleMaidMobX.model.maids;
	public void initModel(float f, float f1, boolean isAfterInit) {
		super.initModel(f, f1, false);
		eyeR = new ModelRenderer(this);
		eyeL = new ModelRenderer(this);
		shield = new ModelRenderer(this);
		if (isAfterInit) afterInit(f, f1);
	}
	public void defaultAddChildSetting() {
		super.defaultAddChildSetting();
		bipedHead.removeChild(eyeR);
		bipedHead.removeChild(eyeL);
		leftArm2.removeChild(shield);
	}
}