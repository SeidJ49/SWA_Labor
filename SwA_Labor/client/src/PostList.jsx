import React from "react";
import { withStyles } from '@material-ui/core/styles';

import Post from './Post'

const styles = theme => ({
	center: {
		display: 'block',
		border: '5px solid blue',
		padding: '10px',
	}
});

class PostList extends React.Component {

	constructor(props) {
		super(props);
	    this.state = {	  
            data: [],
            ownPosts: false,
            noPosition: false,
            loadingPosts: true
		};		
	}
	
  
	render() {
		const { classes } = this.props;
		return (
		<div className={classes.center}>
			<h2>This component renders a list of Post components</h2>
			<Post></Post>
	    </div>
		);
	}
}

export default withStyles(styles)(PostList);

