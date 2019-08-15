import React from 'react'
import { Map, GoogleApiWrapper } from 'google-maps-react'

import './Map.css'

class MapComponent extends React.Component {
	render() {
		return (
			<div id="map-component">
				<h1>This is a map</h1>
                <div id="map">
                    <Map
                        google={this.props.google}
                        zoom={16}
                        //style={mapStyles}
                        initialCenter={{lat: -37.8047, lng: 144.9580}}
                    />
                </div>
			</div>
		)
	}
}

export default GoogleApiWrapper({
    apiKey: 'AIzaSyALA3BO0lJTnnbZNbHVTd4TwSjSNoqo85Q'
})(MapComponent);