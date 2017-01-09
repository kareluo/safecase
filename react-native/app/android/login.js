'use strict';

import React, { Component } from 'react';
import {
  View,
  Text,
  Image,
  StyleSheet,
  StatusBar,
  TextInput,
  BackAndroid,
  TouchableOpacity
} from 'react-native';

export default class LoginComponent extends Component {
  
  componentWillMount() {
    this.handleAction = this.handleAction.bind(this);
  }
  
  render() {
    return (
      <View style={styles.container} >
        <StatusBar backgroundColor='#0079c0'/>
        <Image style={{width:100, height:100}} source={require('image!snow')}/>
        
        <TouchableOpacity
          style = {{margin: 20}}
          onPress={() => this.handleAction({type:'push',key:'main'})}>
          <Text 
            containerStyle={styles.login_button}
            style={styles.login_button}>
            立即体验
        </Text>
        </TouchableOpacity>
        
      </View>
    );
  }
  
  handleAction(action) {
    if(action) {
      const navigation = this.props.navigation;
      if(navigation) {
        navigation.onNavigate(action);
      }
    }
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#009BE1',
  },
  login_button: {
    width: 200,
    height: 48,
    color: 'white',
    fontSize: 18,
    borderRadius: 24,
    justifyContent: 'center',
    alignItems: 'center',
    overflow:'hidden',
    textAlign:'center',
  }
});
