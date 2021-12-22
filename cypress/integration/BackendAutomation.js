describe('How to Hit API Using Cypress',()=>{

it('GET request',()=>{
  cy.request({
    url:'https://gorest.co.in/public/v1/todos'
  }).should((response)=>{
expect(response).property('status').to.eq(200)
expect(response.body.data[0]).have.property('id').to.eq(11)
cy.log(response.body.data[0].title)
  })
})


})