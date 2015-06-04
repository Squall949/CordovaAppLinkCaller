function DeepLinkReceiver() { };

DeepLinkReceiver.init = function () {
    cordova.exec(null, null, 'DeepLinkReceiver', 'Init', []);
};

module.exports = DeepLinkReceiver;