sap.ui.define([ "sap/ui/core/mvc/Controller", "sap/m/MessageToast",
		"sap/m/MessageBox" ], function(Controller, MessageToast, MessageBox) {
	"use strict";
	return Controller.extend("e2e.ui5.controller.CRUD", {

		onInit : function() {},
		
		_peopleTrim : function(p) {
		    return p.replace(/(?:(?:^|\n)\s+|\s+(?:$|\n))/g,'').replace(/\s+/g,'');
		},
		
		onAdd : function() {
			var oModel = this.getView().getModel();
			
			if (this.getView().byId("input01").getValue() == "" || this.getView().byId("input02").getValue() == "" || 
				this.getView().byId("input03").getValue() == "" || this.getView().byId("input04").getValue() == "") {
				MessageBox.warning("All fields are Obligatory");
				
			} else {
				var oEntry = {};
				oEntry.Phone 	= this._peopleTrim(this.getView().byId("input01").getValue());
				oEntry.Name 	= this._peopleTrim(this.getView().byId("input02").getValue());
				oEntry.LastName = this._peopleTrim(this.getView().byId("input03").getValue());
				oEntry.Email 	= this._peopleTrim(this.getView().byId("input04").getValue());
				
				oModel.create('/Peoples', oEntry, {success: function(oData, oResponse){
					MessageBox.success(oEntry.Phone+" was Created");
				  }, error: function(){
					MessageBox.error("Insert Error");
				  }
				});					

				this.getView().byId("input01").setValue("");this.getView().byId("input02").setValue("");
				this.getView().byId("input03").setValue("");this.getView().byId("input04").setValue("");
				this.getView().getModel().refresh();
			}
		},

		onDelete : function(oEvent) {
			var oModel 	= this.getView().getModel();
			var src 	= oEvent.getSource();
			var path 	= src.getBindingContext().getPath();
			var oData 	= oModel.getProperty(path);
			var sPhone 	= oData['Phone'];
			
			oModel.remove(path, {success: function(oData, oResponse){
				MessageBox.success(sPhone+" was Deleted");
			  }, error: function(){
				MessageBox.error("Delete Error");
			  }
			});		
			this.getView().getModel().refresh();
		},

		onUpdate : function(oEvent) {
			var oModel 		= this.getView().getModel();
			var oEntry 		= {};
			oEntry.Name 	= this._peopleTrim(this.getView().byId("input12").getValue());
			oEntry.LastName = this._peopleTrim(this.getView().byId("input13").getValue());
			oEntry.Email 	= this._peopleTrim(this.getView().byId("input14").getValue());
			
			var sPhone 		= this.getView().byId("input11").getText();
			var path 		= this.getView().byId("myModel").getText();
			
			oModel.update(path, oEntry, {success: function(oData, oResponse){
				MessageBox.success(sPhone+" was Updated");
			  }, error: function(){
				MessageBox.error("Update Error");
			  }
			});					
			this.getView().getModel().refresh();
			this.onCloseDialog();
		},

		onOpenDialog : function(oEvent) {
			var oView = this.getView();
			var oDialog = oView.byId("updateDialog");

			var oModel = this.getView().getModel();
			var src = oEvent.getSource();
			var path = src.getBindingContext().getPath();

			var oData 		= oModel.getProperty(path);
			var sPhone 		= oData['Phone'];
			var sName 		= oData['Name'];
			var sLastName 	= oData['LastName'];
			var sEmail		= oData['Email'];

			if (!oDialog) {
				oDialog = sap.ui.xmlfragment(oView.getId(),	"e2e.ui5.view.UpdateDialog", this);
				oDialog.setModel(this.getView().getModel());
				oView.addDependent(oDialog);

				this.getView().byId("input11").setText(sPhone);
				this.getView().byId("input12").setValue(sName);
				this.getView().byId("input13").setValue(sLastName);
				this.getView().byId("input14").setValue(sEmail);
				this.getView().byId("myModel").setText(path);				
			}
			oDialog.open();
		},

		onCloseDialog : function() {
			this.getView().byId("updateDialog").close();
			this.afterClose();
		},

		afterClose : function() {
			this.getView().byId("updateDialog").destroy();
		}

	});
});