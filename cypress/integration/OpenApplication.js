/// <reference types="cypress" />

context('OpenApplication', () => {
    beforeEach(() => {
        cy.log('Opening Browser with app url')
      cy.visit('https://www.google.com') //Open application
      
    })
describe('Feature Google Search',()=>{
it ('Reload Window',()=>{

cy.window().reload
})
it('Find an element based on its locator and enter text in it',()=>{
    cy.get('.gLFyf').type('10 most richest person in india{enter}') //find an element using locator & entering ctrl keys


})
it('Validating item in a of List Elements',()=>{
    cy.get('.V3FYCf > .wDYxhc > .di3YZe > .RqBzHd > .i8Z77e ').first().contains('Mukesh') //validation basic assertion

    

})
it('Scroll with pointer ',()=>{
    cy.scrollTo(1500,1500)
    // cy.get('[data-hveid="CBsQAA"] > .tF2Cxc > .yuRUbf > a > .LC20lb').scrollIntoView()
    

})
it('Execute system commands ',()=>{
    cy.end()
cy.exec('echo %JAVA_HOME%').its('stdout').should('contain', '1.8')

})

  context('Cypress.Screenshot', function () {
    it('cy.screenshot() - take a screenshot', () => {
      // https://on.cypress.io/screenshot
      cy.log('taking snapshot ullu')
      cy.screenshot('my-image')
    })
})
})
})