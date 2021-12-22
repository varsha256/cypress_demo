describe('API GET Request Validation', () => {

//   1.How to hit get request
it('How to hit GET request', () => {

  cy.request({
   url: "https://gorest.co.in/public/v1/users",
   headers:{'Accept':'application/JSON',
  'Content-Type':'application/json'}
  }).should((response)=>{
    expect(response.status).to.eq(200)
  })
})

//   2.How to validate response code

it('How to hit GET request', () => {

  cy.request("https://gorest.co.in/public/v1/users").should((response)=>{
    expect(response.status).to.eq(200)
    expect(response.body.data[0]).has.property('id',69)
  })



  })

  //  3.How to validate response message
  it('How to validate response message',()=>{
  cy.request("https://gorest.co.in/public/v1/users").then((res)=>{
    expect(res.body.data[0]).has.property('id',69)
  })

})

  })

