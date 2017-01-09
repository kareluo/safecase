'use strict';

import React, { Component } from 'react';
import {
  AppRegistry,
  View,
  Navigator,
  StatusBar,
  BackAndroid,
  ToastAndroid,
  NavigationCardStack,
  NavigationExperimental
} from 'react-native';
import LoginComponent from './app/android/login';
import MainComponent from './app/android/main';

// const avatar_url = {uri:"http://ww1.sinaimg.cn/crop.152.120.895.895.1024/005z9fSCjw8euv5uoskivj30xc18std1.jpg"};

class IndexComponent extends Component {
  
  
//   configureScene() {
//     return Navigator.SceneConfigs.PushFromRight;
//   }
  
//   renderScene (route, navigator) {
//     switch(route.id) {
//       case 'login':
//         return <LoginComponent navigator={navigator} />    
//       case 'main':
//         return <MainComponent navigator={navigator} />
//     }
//   }
  
  renderScene (props) {
    ToastAndroid.show("renderScene", ToastAndroid.SHORT);
    return <LoginComponent navigator={navigator} />
  }
  
  render() {
    return (
      <NavigationCardStack  />
    );
  }
}

AppRegistry.registerComponent('IndexComponent', () => IndexComponent);