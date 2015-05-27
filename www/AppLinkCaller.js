function AppLinkCaller() { };

AppLinkCaller.openLink = function (url, onSuccess) {
    cordova.exec(onSuccess, null, 'AppLinkCaller', 'OpenLink', [url]);
};

module.exports = AppLinkCaller;