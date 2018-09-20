import React from "react";
import {Link} from "react-router-dom";
import Logo from "../logo/CHI Unit logo dark.png";
import {Navbar} from 'react-bootstrap';
import {Nav} from 'react-bootstrap';
import {NavItem} from 'react-bootstrap'; //find one import
import {Button} from 'react-bootstrap';
import {FormGroup} from 'react-bootstrap';
import {FormControl} from 'react-bootstrap';
import './main.css';

export default function LandingPageHeader() {
    return (
        <div className="theNav">
            <Navbar>
                <Navbar.Header>
                    <Navbar.Brand>
                        <Link to='/'><img className="img-responsive" src={Logo} alt="logo" height="1"
                                          width="60"/></Link>
                    </Navbar.Brand>
                </Navbar.Header>
                <Nav>
                    <NavItem>
                        <Link to='/courses'>Courses</Link>
                    </NavItem>
                    <NavItem>
                        <Link to='/trainerPage'>Trainer</Link>
                    </NavItem>
                    <NavItem>
                        <Link to='/learner'>Learner</Link>
                    </NavItem>
                </Nav>
                <Navbar.Collapse>
                    <Navbar.Form pullRight>
                        <FormGroup>
                            <FormControl type="text" placeholder="Search for your course..."/>
                        </FormGroup>{' '}
                        <Button type="submit">Submit</Button>
                    </Navbar.Form>
                </Navbar.Collapse>
            </Navbar>

        </div>
    )
}