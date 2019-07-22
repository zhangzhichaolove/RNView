/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, { Component } from 'react';
import { Platform, StyleSheet, Text, View, NativeModules } from 'react-native';
import NativeView from './NativeView'

const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' + 'Cmd+D or shake for dev menu',
  android:
    'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});


export default class App extends Component {
  render() {
    NativeModules.MyView.show()
    // console.log(NativeModules.MyView.abc)
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>Welcome to React Native!</Text>
        <View style={{ backgroundColor: '#000', flexDirection: 'column', }}>
          <Text style={[styles.welcome, { color: '#fff' }]}>Welcome to React Native!</Text>
        </View>
        <NativeView style={{ backgroundColor: '#ff0000', marginLeft: 0, marginTop: 5, width: 160, height: 100 }} text={"这是一个字符串文字。。。。。。。。。。。。"}
          onClick={(event) => alert('原生层传递的数据为：' + event.nativeEvent.msg + '==' + NativeModules.MyView.abc)} />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
