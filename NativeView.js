import React from 'react';
import PropTypes from 'prop-types'
import {
 requireNativeComponent,
 View,
} from 'react-native';

const MyView = requireNativeComponent('MyView', NativeView)

export default class NativeView extends React.Component {

  render() {
    return <MyView  {...this.props}/>
  }
}

// NativeView.propTypes = {
//   ...View.propTypes,
// };