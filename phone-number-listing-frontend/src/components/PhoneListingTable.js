import React from "react";
import { Table, Thead, Th } from "reactable";
import { Input } from "antd";
import axios from "axios";
import configMap from "../config";
import { Pagination } from "semantic-ui-react";

const { Search } = Input;

class PhoneListingTable extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      rowsPerPage: 2,
      data: [],
      totalPages: 1,
      countryName: "",
      valid: "",
      activePage: 1,
    };
    this.onCountrySearch = this.onCountrySearch.bind(this);
    this.onValidSearch = this.onValidSearch.bind(this);
    this.onPageChange = this.onPageChange.bind(this);
  }

  componentDidMount() {
    this.loadData(0);
  }

  loadData(page) {
    const countryString = this.state.countryName
      ? `countryName=${this.state.countryName}`
      : "";
    const validString = this.state.valid ? `valid=${this.state.valid}` : "";

    const url = `${configMap.backend}/customer/phoneNumber?page=${
      page - 1
    }&size=${this.state.rowsPerPage}&${countryString}&${validString}`;
    axios.get(url).then((response) => {
      this.setState({
        data: response.data.content,
        totalPages: response.data.totalPages,
        activePage: page,
      });
    });
  }

  onCountrySearch(searchText) {
    this.setState(
      {
        countryName: searchText,
      },
      () => this.loadData(0)
    );
  }

  onValidSearch(searchText) {
    this.setState(
      {
        valid: searchText,
      },
      () => this.loadData(0)
    );
  }

  onPageChange(event, data) {
    window.scrollTo(0, 0);
    this.loadData(data.activePage);
    console.log(data);
    console.log(event);
  }

  render() {
    return (
      <>
        <Table
          className="table mt-2"
          id="table"
          data={this.state.data.map((row) => {
            return {
              "Phone Number": row.phone,
              Country: row.country.name,
              State: row.validPhone,
            };
          })}
        >
          <Thead>
            <Th column="Phone Number">
              <strong>Phone Number</strong>
            </Th>
            <Th column="Country">
              <strong>Country</strong>
              <Search
                placeholder="input search text"
                onSearch={this.onCountrySearch}
                enterButton
              />
            </Th>
            <Th column="State">
              <strong>State</strong>
              <Search
                placeholder="input search text"
                onSearch={this.onValidSearch}
                enterButton
              />
            </Th>
          </Thead>
        </Table>
        <div
          style={{
            color: "#3f4258",
          }}
          className="row"
        >
          <div className="col-12">
            <Pagination
              totalPages={this.state.totalPages}
              onPageChange={this.onPageChange}
              activePage={this.state.activePage}
            />
          </div>
        </div>
      </>
    );
  }
}

export default PhoneListingTable;
