if (typeof window.AdobeCoding === 'undefined') {
    window.AdobeCoding = {};
}
if (typeof window.AdobeCoding.templates === 'undefined') {
    window.AdobeCoding.templates = {};
}

window.AdobeCoding.templates.galleryTemplate = [
	'{{#each info}}',
		'<div class="card">',
	    '<div class="thumb" style="background-image: url({{this.path}});"></div>',
	    '<article>',
	      '<h2>{{this.title}}</h2>',
	      '<p>{{this.description}}</p>',
	      '<span>{{this.mimeType}}</span>',
	    '</article>',
    '</div>',
	'{{/each}}'].join("\n");


	