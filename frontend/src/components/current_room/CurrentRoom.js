import React from 'react';
import axios from 'axios';
import { getToken } from '../app/useToken';
import { getUserId } from '../app/useUserId';
import PlayerInfo from '../player/PlayerInfo';
import Table from 'react-bootstrap/Table';
import Spinner from 'react-bootstrap/Spinner';
import { withRouter } from 'react-router-dom'
import Button from 'react-bootstrap/Button';
import { ToastContainer, toast } from 'react-toastify';
import Modal from 'react-bootstrap/Modal';
import PlayerSocial from '../player/PlayerSocial';

class CurrentRoom extends React.Component {
    intervalID;

    state = {
        gameRooms: [],
        time: null,
        isRoomFull: false,
        showPlayersInfo: false
    }


    getData = () => {
        const token = getToken()
        const userId = getUserId()
        axios.get('http://localhost:8080/room/newest/' + userId, {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
            .then(res => {
                const gameRooms = res.data;
                this.setState({ gameRooms });
            })
        this.intervalID = setTimeout(this.getData.bind(this), 1000);
    }

    leaveRoom = () => {
        const token = getToken()
        const userId = getUserId()
        const roomId = this.state.gameRooms[0].id
        axios.get('http://localhost:8080/room/' + roomId + "/" + userId, {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Authorization': 'Bearer ' + token
            }
        })
        this.props.history.push('/main')
    }

    handleClose = () => {
        this.setState({ viewPlayersInfo: false })
    }  

    viewPlayersInfo = () => {
        this.setState({ viewPlayersInfo: true })
    }

    componentDidMount() {
        this.getData()
    }


    componentWillUnmount() {
        clearTimeout(this.intervalID);
    }

    getCreationDate() {
        var date = new Date(this.state.gameRooms[0].creationDate)
        var d = date.toLocaleDateString()
        var t = date.toLocaleTimeString()
        return d + ", " + t
    }

    render() {
        if (this.state.gameRooms.length > 0) {
            if (this.state.gameRooms[0].playersList.length < this.state.gameRooms[0].maxPlayers) {
                return (
                    <div style={{ margin: 'auto', width: '50%' }}>
                        <div style={{ textAlign: 'center', marginBottom: '3em' }}>
                            <h1>Waiting for other players...</h1>
                            <p>Creation date: {this.getCreationDate()}</p>
                            <Spinner animation="border" role="status" size="lg">
                                <span className="visually-hidden">Loading...</span>
                            </Spinner>
                            <h1>{this.state.gameRooms[0].playersList.length} / {this.state.gameRooms[0].maxPlayers}</h1>
                        </div>
                        <h3>{this.state.gameRooms[0].game.gameName} </h3>
                        <h4>{this.state.gameRooms[0].gameType}</h4>
                        <div>
                            <Table striped bordered hover responsive>
                                <thead>
                                    <tr>
                                        <th style={{textAlign: 'center', verticalAlign: 'middle'}}>Username</th>
                                        <th style={{textAlign: 'center', verticalAlign: 'middle'}}>Rank</th>
                                        <th style={{textAlign: 'center', verticalAlign: 'middle'}}>Level</th>
                                    </tr>
                                </thead>
                                {this.state.gameRooms[0].playersList.map(player => (
                                    <tr style={{textAlign: 'center', verticalAlign: 'middle'}}>
                                        <PlayerInfo player={player} />
                                    </tr>
                                ))}
                            </Table>
                        </div>
                        <div style={{ float: 'left', marginRight: '3em' }}>
                            <Button variant="danger" visible={false} onClick={this.leaveRoom}>Leave room</Button>
                        </div>
                        <div>
                            {this.state.isRoomFull ? <Button variant="primary" onClick={this.leaveRoom}>View players info</Button> : null}
                        </div>
                        <ToastContainer
                            position="bottom-center"
                            autoClose={5000}
                            hideProgressBar={false}
                            newestOnTop={false}
                            closeOnClick
                            rtl={false}
                            pauseOnFocusLoss
                            draggable
                            pauseOnHover
                        />
                    </div>
                )
            } else if (this.state.gameRooms[0].roomStatus == "CLOSED") {
                return (
                    <>
                        <div style={{ margin: 'auto', width: '50%' }}>
                            <div style={{ textAlign: 'center', marginBottom: '3em' }}>
                                <h1>All players found!</h1>
                                <p>Creation date: {this.getCreationDate()}</p>
                            </div>
                            <h3>{this.state.gameRooms[0].game.gameName} </h3>
                            <h4>{this.state.gameRooms[0].gameType}</h4>
                            <div>
                                <Table striped bordered hover responsive>
                                    <thead>
                                        <tr>
                                            <th>Username</th>
                                            <th>Rank</th>
                                            <th>Level</th>
                                        </tr>
                                    </thead>
                                    {this.state.gameRooms[0].playersList.map(player => (
                                        <tr>
                                            <PlayerInfo player={player} />
                                        </tr>
                                    ))}
                                </Table>
                            </div>
                            <div>
                                <Button variant="primary" onClick={this.viewPlayersInfo}>View players info</Button>
                            </div>
                        </div>
                        <div>
                            <Modal show={this.state.viewPlayersInfo} onHide={this.handleClose}>
                                <Modal.Header closeButton>
                                    <Modal.Title>User's social:</Modal.Title>
                                </Modal.Header>
                                <Modal.Body>
                                    <div>
                                        <PlayerSocial playersList={this.state.gameRooms[0].playersList}/>
                                    </div>
                                </Modal.Body>
                                <Modal.Footer>
                                    <Button variant="secondary" onClick={this.handleClose}>
                                        Close
                                    </Button>
                                </Modal.Footer>
                            </Modal>
                        </div>
                    </>
                )
            }
        }
        else {
            return (
                <div style={{ margin: 'auto', width: '50%' }}>
                    <h1>Join to existing game room or create your own</h1>
                </div>
            )
        }
    }
}

export default withRouter(CurrentRoom)



