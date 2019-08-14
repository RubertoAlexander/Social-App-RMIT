import React from 'react'
import { Map, GoogleApiWrapper } from 'google-maps-react';

class MapComponent extends React.Component {
	render() {
		return (
			<div>
				<h1>This is a map</h1>
                <Map
                    google={this.props.google}
                    zoom={16}
                    //style={mapStyles}
                    initialCenter={{lat: -37.8047, lng: 144.9580}}
                />
			</div>
		)
	}
}

export default GoogleApiWrapper({
    apiKey: 'AIzaSyALA3BO0lJTnnbZNbHVTd4TwSjSNoqo85Q'
})(MapComponent);