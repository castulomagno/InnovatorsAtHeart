sap.ui.define([
	'jquery.sap.global',
	'sap/ui/core/Fragment',
	'sap/ui/core/mvc/Controller',
	'sap/ui/model/json/JSONModel',
	'sap/m/Popover',
	'sap/m/Button'
], function (jQuery, Fragment, Controller, JSONModel, Popover, Button) {
	"use strict";
 
	var CController = Controller.extend("sport.games.admin.ui5.controller.Admin", {
		model : new sap.ui.model.json.JSONModel(),
		data : {
			navigation: [{
				title: 'Main',
				icon: 'sap-icon://world',
				expanded: true,
				key: 'root1'
			}, {
				title: 'Tournaments',
				icon: 'sap-icon://competitor',
				expanded: false,
				key: 'root2',
				items: [{
					title: 'Add Tournament',
					key: 'page1'
				}, {
					title: 'List of Tournaments',
					key: 'page2'
				}]
			},
			{
				title: 'Leagues',
				icon: 'sap-icon://puzzle',
				expanded: false,
				key: 'root3',
				items: [{
					title: 'Add League',
					key: 'page1'
				}, {
					title: 'List of Leagues',
					key: 'page2'
				}]
			}, {
				title: 'Teams',
				icon: 'sap-icon://shield',
				expanded: false,
				key: 'root4',
				items: [{
					title: 'Add Team',
					key: 'page1'
				}, {
					title: 'List of Teams',
					key: 'page2'
				}]
			}, {
				title: 'Players',
				icon: 'sap-icon://collaborate',
				expanded: false,
				key: 'root5',
				items: [{
					title: 'Add Player',
					key: 'page1'
				}, {
					title: 'List of Players',
					key: 'page2'
				}]
			}, {
				title: 'Matchs',
				icon: 'sap-icon://date-time',
				key: 'root6'
			}, {
				title: 'Standing',
				icon: 'sap-icon://numbered-text',
				key: 'root7'
			}],
			fixedNavigation: [{
				title: 'Fixed Item 1',
				icon: 'sap-icon://employee'
			}, {
				title: 'Fixed Item 2',
				icon: 'sap-icon://building'
			}, {
				title: 'Fixed Item 3',
				icon: 'sap-icon://card'
			}],
			headerItems: [
			{
				text: "File"
			}, {
				text: "Edit"
			}, {
				text: "View"
			}, {
				text: "Settings"
			}, {
				text: "Help"
			}]
		},
		
		onInit : function() {
			this.model.setData(this.data);
			this.getView().setModel(this.model);
 
			this._setToggleButtonTooltip(!sap.ui.Device.system.desktop);
		},
 
		onItemSelect : function(oEvent) {
			var item = oEvent.getParameter('item');
			var viewId = this.getView().getId();
			sap.ui.getCore().byId(viewId + "--pageContainer").to(viewId + "--" + item.getKey());
		},
 
		handleUserNamePress: function (event) {
			var popover = new Popover({
				showHeader: false,
				placement: sap.m.PlacementType.Bottom,
				content:[
					new Button({
						text: 'Feedback',
						type: sap.m.ButtonType.Transparent
					}),
					new Button({
						text: 'Help',
						type: sap.m.ButtonType.Transparent
					}),
					new Button({
						text: 'Logout',
						type: sap.m.ButtonType.Transparent
					})
				]
			}).addStyleClass('sapMOTAPopover sapTntToolHeaderPopover');
 
			popover.openBy(event.getSource());
		},
 
		onSideNavButtonPress : function() {
			var viewId = this.getView().getId();
			var toolPage = sap.ui.getCore().byId(viewId + "--toolPage");
			var sideExpanded = toolPage.getSideExpanded();
 
			this._setToggleButtonTooltip(sideExpanded);
 
			toolPage.setSideExpanded(!toolPage.getSideExpanded());
		},
 
		_setToggleButtonTooltip : function(bLarge) {
			var toggleButton = this.getView().byId('sideNavigationToggleButton');
			if (bLarge) {
				toggleButton.setTooltip('Large Size Navigation');
			} else {
				toggleButton.setTooltip('Small Size Navigation');
			}
		}
 
	});
 
 
	return CController;
 
});