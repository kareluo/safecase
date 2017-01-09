'use strict';

import React, {
  Component
} from 'react';
import {
  Text,
  StyleSheet,
  ScrollView,
  AppRegistry,
  ToastAndroid,
  BackAndroid,
  NavigationExperimental
} from 'react-native';
import LoginComponent from './app/android/login';
import MainComponent from './app/android/main';

const {
  CardStack: NavigationCardStack,
  StateUtils: NavigationStateUtils
} = NavigationExperimental;

const LoginState = {
  index: 0,
  key: 'login',
  children: [{key: 'login'}],
}

const ExampleReducer = function createReducer(currentState = LoginState, action) {
  switch (action.type) {
    case 'push':
      return NavigationStateUtils.push(currentState, {key: action.key});
    case 'back':
    case 'pop':
      return currentState.index > 0 ?
        NavigationStateUtils.pop(currentState) : currentState;
    default:
      return currentState;
  }
}

class IndexComponent extends React.Component {
  
  constructor(props, context) {
    super(props, context);
    this.state = {
      navState : ExampleReducer(undefined, {})
    };
  }
  
  componentWillMount() {
    this.renderScene = this.renderScene.bind(this);
    this.handleAction = this.handleAction.bind(this);
  }
  
  componentDidMount() {
    BackAndroid.addEventListener('hardwareBackPress', ()=>{
      if(this.state.navState.index > 0) {
        this.handleAction({ type: 'back', });
        return true;
      }
      return false;
    });
  }
  
  componentWillUnmount() {
    BackAndroid.removeEventListener('hardwareBackPress');
  }
  
  renderScene(props) {
    const nstate = this.state.navState;
    switch(nstate.children[nstate.index].key) {
      case 'login':
        return <LoginComponent navigation={props}/>
      case 'main':
        return <MainComponent navigation={props}/>
    }
  }
  
  render() {
    return (
      <NavigationCardStack
        navigationState={this.state.navState}
        onNavigate={this.handleAction}
        renderScene={this.renderScene}
        style={{flex: 1}}
      />
    );
  }

  handleAction(action): boolean {
    if (!action) return false;
    const newState = ExampleReducer(this.state.navState, action);
    if (newState === this.state.navState) {
      return false;
    }
    this.setState({
      navState: newState,
    });
    return true;
  }
}

AppRegistry.registerComponent('IndexComponent', () => IndexComponent);