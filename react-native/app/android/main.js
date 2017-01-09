'use strict';

import React, {
  Component
} from 'react';
import {
  View,
  Text,
  Image,
  StyleSheet,
  ToolbarAndroid,
  ToastAndroid,
  ListView,
  TouchableOpacity,
  NativeModules,
  ActivityIndicatorIOS,
} from 'react-native'

var feeds = [
  {
    icon: 'image!snow'
  },
  {
    icon: 'image!snow'
  },
  {
    icon: 'image!snow'
  },
  {
    icon: 'image!snow'
  },
  {
    icon: 'image!snow'
  },
  {
    icon: 'image!snow'
  }
];

export default class MainComponent extends Component {
  
  constructor() {
    super();
    var ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
    this.state = {
      dataSource: ds.cloneWithRows(feeds)
    };
  }
  
  componentWillMount() {
    this.toAdd = this.toAdd.bind(this);
    this.renderRow = this.renderRow.bind(this);
    this.handleAction = this.handleAction.bind(this);
    this.onActionSelected = this.onActionSelected.bind(this);
  }
  
  render() {
    return (
      <View style={styles.container}>
        <ToolbarAndroid 
          navIcon={require('image!ic_back')}
          style={{height: 56, backgroundColor: '#2198c8'}}
          title="密码"
          onIconClicked={() => this.handleAction({type: 'back',})}
          actions={[{title: '添加', icon:require('image!ic_action_add'), show: 'always'}]}
          titleColor='#eeeeee'
          onActionSelected={this.onActionSelected}/>
        
        <ListView 
          dataSource={this.state.dataSource}
          renderRow={this.renderRow}/>
      </View>
    );
  }
  
  onActionSelected(position) {
    switch(position) {
      case 0:
        this.toAdd();
    }
  }
  
  toAdd() {
    NativeModules.AppNavigatorModule.startActivityForResult('me.kareluo.safecase.NativeActivity', (err, success)=>{
      if(err) {
        console.log(err);
      } else {
        ToastAndroid.show(success, ToastAndroid.SHORT);
      }
    })
  }
  
  handleAction(action) {
    if(action) {
      const navigation = this.props.navigation;
      if(navigation) {
        navigation.onNavigate(action);
      }
    }
  }
  
  renderRow(rowData) {
    return (
      <TouchableOpacity>
        <View style={styles.item}>
          <View style={{flex:1, flexDirection: 'row'}}>
            <Image style={{width: 100, height: 100}} source={require('image!snow')}/>
            <Text style={{margin: 30}}>{'这里是标题'}</Text>
          </View>
          <View style={{height: 1, backgroundColor: '#eee'}}/>
        </View>
      </TouchableOpacity>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#eeeeee',
  },
  item: {
    backgroundColor: '#fff'
  }
});